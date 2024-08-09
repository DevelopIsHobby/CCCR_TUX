# 베이스 이미지 설정
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 현재 디렉토리의 .jar파일을 파드 안으로 복사
COPY /home/user/cicd/tux_weather_test-0.0.3.jar /app/weather.jar

# 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "/app/weather.jar"]

