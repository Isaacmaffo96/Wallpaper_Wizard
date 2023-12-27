# Wallpaper Wizard
Wallpaper Wizard is a Java application that allows you to download NASA's Astronomy Picture of the Day (APOD) and set it as your wallpaper.

## Features
- Download NASA's Astronomy Picture of the Day.
- Set the downloaded image as your wallpaper.

## Getting Started
To get started with Wallpaper Wizard, follow these steps:

### Prerequisites
- Java Development Kit (JDK 17) installed.
- Maven for building the project.

### Installation and Usage
1. Clone or download the Wallpaper Wizard repository.

2. Obtain NASA API Key:
   - Go to [NASA API Portal](https://api.nasa.gov/) .
   - Sign up for an API key.
   - Copy your API key.

3. Set up API Key in application.properties:
    - Open the src/main/resources/application.properties file.
    - Add your NASA API key:
    ```
    application.properties
    user.nasa.api.key=YOUR_API_KEY_HERE
    ```
4. Build and Run the Application:
   - Open a terminal in the project directory.
   - Run mvn clean install to build the project.
   - Run mvn spring-boot:run to start the application.

5. Setting Wallpaper:
   - The application will download and set the image as your wallpaper automatically.

6. Access the downloaded image
   - The downloaded image is saved in the path C:\Users\USER\Documents\Wallpapers.
   - replace user with your user.
  
### Customization
1. Resize the image:
   - optional: the application works even without resizing.
   - lockScale params: true doesn't change the scale, false change the scale to match the screen sizes.
    ``` java
    Main.java
    // Resize the image
    Resizer.resizeImage(imageSaver.getDownloadedImagePathString(), lockScale: false);
    ```
## Technologies Used
- Java
- Spring Boot
- Maven

### External libraries
- [Java Native Access (JNA)](https://github.com/java-native-access/jna): provides Java programs easy access to native shared libraries without writing anything but Java code

## Future developments
- [ ] Give the possibility to choose the image of the day from multiple sites (example bing.com)
- [ ] Add a graphical interface to simplify user interaction
- [ ] Automate the application to change wallpaper every day
