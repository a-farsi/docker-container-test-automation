FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy Java source file
COPY PermissionsTest.java .

# Compile the Java code
RUN javac PermissionsTest.java

# Set environment variable for logging
#ENV LOG=1

# Crée le dossier logs dans /app
RUN mkdir -p /app/logs

# Run the Java program
CMD ["java", "PermissionsTest"]
