# OptiRigFit: Java-Driven Personalized PC Hardware Configuration Builder

<p align="center">
  <img src="data/resource/AppImageResize.png" width="350" title="OptiRigFit®">
</p>

OptiRigFit project aims to build a **desktop hardware configuration generator** to help *layman* and PC building 
*novices* to figure out the **best** and **tailored** configuration to their specific needs and budget. The goal 
is to ensure that **every user**, regardless of their **technical background**, can confidently make informed decisions 
and achieve optimal PC performance. 


## Table of Contents
- [Installation & Manual](#installation--manual)
- [Database Coverage](#database-coverage-sept-28th-2023)
- [User Stories Records](#user-story)
- [Instructions for Grader](#instructions-for-grader)
  1. [Generate required action related to the user story](#1generate-the-required-action-related-to-the-user-story)
  2. [Visual component](#2-visual-component)
- [Phase 4: Task 3](#phase-4-task-3)
- [Data Sources](#data-sources)

## Project Expectation & ToDoList
- Database of RAM, GPU upload ✅
- Configuration recommendation algorithm ✅
- Sort item by price or performance ✅
- console interface ✅
- GUI ✅

## Installation & Manual
Java Desktop App
- Clone the repo from the GitHub: https://github.students.cs.ubc.ca/CPSC210-2023W-T1/project_h9k6r.git
- Open with Intellij
- Change to package view
- Navigate to **ui** package under main folder in src
- Run Main file

## Database coverage (Sept 28th, 2023)
- CPU:
  - Intel: 12th Core Desktop CPU & 13th Core Desktop
  - AMD 7000 series Ryzen Desktop CPU
- Motherboard:
  - ASUS: LGA1700 Z690 H670 B660 Z790 H770 B760
  - ASUS: AM5 X670 B650 B620 on sale
- Power Supply:
  - ASUS: Loki Thor
  - Corsair: SF HI
  - EVGA
- GPU:
  - NVIDIA: 30 40 series desktop GPU
  - AMD: 6000 700 series desktop GPU
- RAM:
  - Purchase suggestions:
    - Max motherboard ram capacity
    - Number of slot on motherboard


## User Story
1. As a user, I want to be able to add arbitrary configurations(X) to saved list of configurations (Y)
2. As a user, I want to be able to generate arbitrary recommended configurations(X)
3. As a user, I want to be able to review saved configuration
4. As a user, I want to be able to add optional (extra) components
5. As a user, I want to be able to change necessary components
6. As a user, I want to be able to customize generated configurations
7. As a user, I want to be able to choose the size of computer
8. As a user, I want to be able to choose the purpose of computer
9. As a user, I want to be able to input budget
10. As a user, when I select the quit option from the application menu, I want to be reminded to save configuration list to file and have the option to do so or not.
11. As a user, when I start the application, I want to be given the option to load my configuration list from file.

## Instructions for Grader

### 1.Generate the required action related to the user story
- You can generate the first required action related to the user story "adding multiple Xs to a Y" (user story #2, 7, 8, 9) by
  "Configuration" -> "build new config" (Keyboard shortcut: Control + N)
  - Generate new config (user story #2, #7, #8, #9)
    1. go to toolbar "Configuration" -> "build new config" (Keyboard shortcut: Control + N)
    2. input valid max 5 digits budget (with max two decimal places)
    3. select prefer purpose from four button on popup window
    4. select prefer size from three button on popup window
- You can generate the second required action related to the user story "adding multiple Xs to a Y" (user story #1) by
  clicking the "save" button on each generate config internal panel which you would like to save
  - Save arbitrary Xs to Y (user story #1)
    1. follow above procedure of generating x as arbitrary times
    2. click the "save" button on each generate config internal panel which you would like to save
    3. check whether the number of config showing up on the saving queue
- Customize your own config (user story #3)
  1. click the "customize" button on the config internal panel on workspace
  2. click the comb box of the component you would like to change
  3. click the "save" button to save your change to the configuration
- Check the saved configs again (user story #5, #6)
  1. click the number of saved configs in the saving queue you would check to check again (closed config on workspace)
  2. click the "show" button on the bottom of saving queue panel
- You can save the state of my application by clicking the "file" -> "load file to workspace" option on toolbar of "file"
  - Save Y to the file (persistence) (user story #10)
    1. check the saving queue of item you would like to save
    2. go to the toolbar "file" -> "save list to file" (Control + S)
- You can reload the state of my application by clicking "save list to file" option on toolbar of "file"
  - Load Y from the file (user story #11)
    1. go to the toolbar "file" -> "load file to workspace" (Control + O)
    2. loaded saved configurations would be sync to the saving queue
### 2. Visual component
- Relative dictionary: [./data/resource](./data/resource)
- Github dictionary: [https://github.students.cs.ubc.ca/CPSC210-2023W-T1/project_h9k6r/blob/dcdd99538e6691cc203fa92e40d5f332f1c63355/data/resource](https://github.students.cs.ubc.ca/CPSC210-2023W-T1/project_h9k6r/blob/dcdd99538e6691cc203fa92e40d5f332f1c63355/data/resource)

## Phase 4: Task 3
For those classes for components, they could be refactored to reduce the redundant lists of components database class to reduce the coupling and increase the cohesion.
Extract a general abstract class of component with model name, sale price, and base power. Extract a general list of components which has filter methods to filter the compatible 
component during the configuration generation. All concrete component class extends the abstract class of general components to increase cohesion. Only add unique property to concrete
component class, like socket for cpu and motherboard, size for motherboard and power supply, etc. This could improve the efficiency of algorithm for figuring out the 
best fit configuration when this app was deployed on web with high demand of request. 

For configuration saving queue, it could be improved by applying singleton pattern to keep only one instance in both console and Ui class. The saving queue is the only one which store
the list of configuration. It is modified only when user generate new configurations or load the list of saved configurations to the workspace. Maintain the single instance would be 
helpful to reduce the coupling. 

## Data Sources
- Prices Reference: (CAD$)：
- Amazon: https://amazon.ca 
- Canada Computer: https://canadacomputers.com
- New Egg: 
- CPU:
  - Intel: https://ark.intel.com/content/www/us/en/ark.html#@PanelLabel122139
  - AMD: https://www.amd.com/en/products/specifications/processors
- Motherboard:
  - ASUS: https://rog.asus.com/ca-en/motherboards-group/
- Power Supply:
  - Corsair: https://www.corsair.com/us/en
  - EVGA: https://www.evga.com/products/productlist.aspx?type=10
  - ASUS: https://rog.asus.com/event/PSU/ASUS-Power-Supply-Units/global/index.html
  - Cooler Master: https://www.coolermaster.com/catalog/power-supplies/
- GPU:
  - Nvidia: https://store.nvidia.com/en-ca/geforce/store/?page=1&limit=9&locale=en-ca&category=GPU,DESKTOP
  - AMD: https://shop-ca-en.amd.com/
