1. SDK Tools Required: Google Play services (version 49) select Tools-SDK Manager-SDK-Tools
2. Everytime before you launch the app, you need to set your location on the emulator manually. (Click the 3 dots above the emulator and select location).
3. Emulator: Pixel 3 API 30
4. We have a sample user. Username: Leo; Password: 1223 You may examine the order history and profile function with this user. These two functions do not work particularly well on just created users due to server issues(see below)
We also have a sample merchant. Username: Boba God  Password:123
5. Change the path of the jar file with the actual path of the jar file in your local computer.
6. Our data base is deployed on AWS. Since we selected the free server, there are often significant delay of data, which can causes crashe since we often immediately pull just inserted data from the cloud database. We are actively working on a solution and will look more into faster servers in the future.