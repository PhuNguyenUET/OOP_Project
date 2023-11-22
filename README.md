# English-learning application
## Description: 
An educational application that supports language learners in the acquisition of vocabulary
- Actor: Single user on Desktop across various operating systems
- Business logic: Single user: Log in, sign up; dictionary, create and manage folders and lists of words; translate texts; educational games; profile (learning time and streaks); settings (change username, avatar, password)
## Tech Stack:
- Language: Java
- Database: Sqlite, MySQL Server
- Backend: JDBC, Jackson, Google Translate API, FreeTTS
- Frontend: JavaFX, Jackson
- Build tool: maven
- Design pattern: 3-layer architecture, C4 Model, UML Diagram
- Operating system: Linux & Windows
- Source version control: Git
## Contributors:
- Nguyen Huu Phu (22021138) - Team leader: Design project structures via C4 Model, take responsibilities for tab Dictionary, Translate, Multiple Choice, Settings and some other minor functions
- Ngo Thanh Minh (22021131): Take responsibilities for tab Login, Learners' path, Flip game, Profile, Navigation Bar and some other minor functions
- Luong Ngoc Tuan (22021146): Manage databases, search and add information to databases, draw UML Diagaram, testing and reporting bugs
## Implementations:
- Window:
  - First, you need to install the jdk.msi file
  - After that, extract the .zip file and click on run.bat to start experiencing the application.
  ![Screenshot (640)](https://github.com/PhuNguyenUET/OOP_Project/assets/124753460/1dbfd6dd-7759-4ab7-b62f-d34ad074e591)
- Linux:
  In general, implementations on Linux is rather similar to Windows, however, you would need to install packages to support reading mp3 files (ffplay for ubuntu) and you would sometimes be required to manually unpack the library, so using this application on Linux operating systems is generally not recommended. Sorry for any inconvenience this may have caused.
### UML Diagram:
![class_diagram](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/269d24eb-6c09-44f6-b082-bd91eb5ad71c)

## Features:
### Video demo
[Demo](https://drive.google.com/file/d/1lbWtM1D_ZM1wMCHzWK2195tkuV0f6_-X/view?usp=drive_link)
### Login & Sign-up:
![Login](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/185dbfd7-6aed-431b-9049-c987fcef4d56)
### Learners' Path:
This is the part where learners create and manage folders and lists to help with vocabulary learning:

![LearnersHome](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/0a7e1eaf-ebea-47de-b4bc-b72e597e3933)

![Folder](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/416d9907-ba2d-4198-9406-e53f83cff0bc)

![Words](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/41550684-89eb-4bc7-bad5-f24b35b9aca7)

### Dictionary:
![DictionaryHome](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/e847e27e-50a2-4040-b3d4-1e6c36ba0bc8)

![Wordsearch](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/ce274dce-f362-4e64-b3d8-e1017ab44e9a)

### Games:
Educational games that allows for revision while having fun, too.
![GameSelect](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/efd8c775-acba-4d39-a27b-1691192f8174)

![FlipGame](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/c8e18661-368f-48b9-92f8-1239f81c14ba)

![MultipleChoice](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/237a9d1a-8c59-4f88-92e4-395c789c27c2)

### Translate:
![Translate](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/9822333b-29de-4d2a-a1a7-88cdc65c0fe3)

### Settings:
![Settings](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/3972bb98-314d-4ca2-bba0-b965a82d9d1b)

### Profile:
![Profile](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/1ec60034-e3ff-4300-b13b-4d42e48859d3)

### Special Christmas Event :)
![Christmas-event](https://github.com/PhuNguyenUET/OOP_Project/assets/115403554/a058402d-a539-466a-a4c0-803336319b17)


