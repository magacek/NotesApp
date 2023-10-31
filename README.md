**Project 8
**

**Description of the Project**
Building upon Project 6, Project 8 enhances the Notes App by integrating it with Firebase. This update allows for real-time synchronization of notes across devices and user authentication to provide a personalized experience. The app now allows users to create an account, sign in, and have their notes saved securely in Firebase's realtime database.

**Functionality**
The following required functionality is completed:

[Firebase Realtime Database]
[Firebase Authentication]
[Notes CRUD Operations]
[UI for Viewing and Editing Notes]
[User Authentication Screen]

**The following extensions are implemented:**


LiveData for real-time UI updates.
ViewModel to manage the UI-related data.
Firebase integration for real-time data synchronization across devices.
Adapter pattern with RecyclerView for efficient listing of notes in a staggered grid layout.
User authentication flow for personalized note management.
Changes from Project 6

Shifted from using Room Database to Firebase's realtime database for storing notes.
Added user authentication features allowing users to sign up, sign in, and sign out.
Updated the UI to include an authentication screen and added a sign-out option.
Enhanced the notes listing to use a StaggeredGridLayoutManager with 2 columns.
Implemented real-time synchronization of notes to reflect changes across different devices instantly.

**Video Walkthrough**
Here's a walkthrough of implemented user stories:

![Project%208%20Video](https://github.com/magacek/NotesApp/assets/70607808/16e43a0c-953c-4222-b8ac-5081f1654872)


GIF created with LiceCap.

**Notes**
Describe any challenges encountered while building the app.

Integrating Firebase for both authentication and real-time data persistence posed new challenges, such as ensuring data consistency and managing user sessions effectively.

License

**sql**
Copy code
 Copyright [2023] [Mateusz Gacek]

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
