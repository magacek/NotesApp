Project 6

Description of the project
Building on the foundations laid in the previous projects, Project 6 is a Notes App that allows users to create, view, edit, and delete notes. The project leverages Android's Room persistence library to provide a local database, ensuring that the notes are stored securely and can be accessed efficiently.

Functionality
The following required functionality is completed:

[Notes Database]
[Note CRUD Operations]
[UI for Viewing and Editing Notes]
The following extensions are implemented:

LiveData for real-time UI updates.
ViewModel to manage the UI-related data.
Room Database for local data persistence.
Adapter pattern with RecyclerView for efficient listing of notes.
Data Binding for UI components.
Video Walkthrough
Here's a walkthrough of implemented user stories:
![project6](https://github.com/magacek/NotesApp/assets/70607808/173d4026-eb10-48f3-8c8d-42a4bf2cd6f5)


project6

GIF created with LiceCap.

Notes
Describe any challenges encountered while building the app.

One of the challenges faced during the development of this project was integrating the Room Database with LiveData and ensuring that the UI updates in real-time when the database changes. Additionally, handling data persistence and ensuring that notes are updated accurately in the database required careful implementation.

License
sql
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
