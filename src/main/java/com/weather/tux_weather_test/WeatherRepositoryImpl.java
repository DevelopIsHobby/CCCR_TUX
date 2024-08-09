package com.weather.tux_weather_test;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Log4j2
@Component
public class WeatherRepositoryImpl implements WeatherRepository{
    private static Connection connect()
    {
        Connection conn = null;
        try
        {
            Dotenv dotenv = Dotenv.load();
            String user = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");
            String url = dotenv.get("DB_URL");
            String sURL = "jdbc:machbase://"+url+"/machbasedb";
            log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ url?"+url);
            System.out.println("~~~~url?"+url);

            Properties sProps = new Properties();
            sProps.put("user", user);
            sProps.put("password", password);
            log.info("user????????/"+user+password);
            Class.forName("com.machbase.jdbc.MachDriver");
            conn = DriverManager.getConnection(sURL, sProps);
        }
        catch ( ClassNotFoundException ex )
        {
            System.err.println("Exception : unable to load mariadb jdbc driver class");
        }
        catch ( Exception e )
        {
            System.err.println("Exception : " + e.getMessage());
        }
        return conn;
    }
    @Override
    public WeatherDTO getWeatherDTO() throws Exception
    //public static void main(String[] args) throws Exception
    {
        Connection conn = null;
        System.out.println("main.......dto..");
        Statement stmt = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WeatherDTO weatherDTO = null;
        try
        {
            conn = connect();
            if( conn != null )
            {
                System.out.println("machdb JDBC connected.");
                //select
                //String query = "SELECT d1, d2, d3, f1, f2, name, text, bin, to_hex(bin), v4, v6, to_char(dt,'YYYY-MM-DD HH24:MI:SS mmm:uuu:nnn') as dt from SAMPLE_TABLE";
                stmt = conn.createStatement();
                //String query = "SELECT * from weather_data";
                String query = "select time, address, temp, feeling, humidity, status, dust, mini_dust, uv, rain from weather_data limit 1";
                ResultSet rs = stmt.executeQuery(query);
                System.out.println(rs);



                while( rs.next () )
                {
                    String time = rs.getString("time");
                    String address = rs.getString("address");
                    Float temp =  rs.getFloat("temp");
                    Float feeling = rs.getFloat("feeling");
                    Float humidity = rs.getFloat("humidity");
                    String weather = rs.getString("status");
                    String dust = rs.getString("dust");
                    String mini_dust = rs.getString("mini_dust");
                    String uv = rs.getString("uv");
                    Float rain = rs.getFloat("rain");

                    weatherDTO = WeatherDTO.builder()
                            .time(time)
                            .address(address)
                            .temp(temp)
                            .feeling(feeling)
                            .humidity(humidity)
                            .weather(weather)
                            .dust(dust)
                            .mini_dust(mini_dust)
                            .uv(uv)
                            .rain(rain)
                            .build();
                }
                rs.close();
            }
        }
        catch( Exception e )
        {
            System.err.println("Exception : " + e.getMessage());
        }
        finally
        {
            if( conn != null )
            {
                conn.close();
                conn = null;
            }
        }

        return weatherDTO;
    }
}
