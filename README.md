# OptiRigFit: Java-Driven Personalized PC Hardware Configuration Builder
OptiRigFit project aims to build a **desktop hardware configuration generator** to help *layman* and PC building 
*novices* to figure out the **best** and **tailored** configuration to their specific needs and budget. The goal 
is to ensure that **every user**, regardless of their **technical background**, can confidently make informed decisions 
and achieve optimal PC performance. 


## Table of Contents
- [Installation & Manual](#installation--manual)
- [Database Coverage](#database-coverage-sept-28th-2023)
- [User Stories Records](#user-story)
- [Data Sources](#data-sources)

## Project Expectation & ToDoList
- Database of RAM, GPU upload ✅
- Configuration recommendation algorithm ✅
- Sort item by price or performance ✅
- console interface ✅
- GUI

## Installation & Manual
Java Desktop App
- Clone the repo from the Github: https://github.students.cs.ubc.ca/CPSC210-2023W-T1/project_h9k6r.git
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
- As a user, I want to be able to add arbitrary configurations(X) to saved list of configurations (Y)
- As a user, I want to be able to generate arbitrary recommended configurations(X)
- As a user, I want to be able to review saved configuration
- As a user, I want to be able to add optional (extra) components
- As a user, I want to be able to change necessary components
- As a user, I want to be able to customize generated configurations
- As a user, I want to be able to choose the size of computer
- As a user, I want to be able to choose the purpose of computer
- As a user, I want to be able to input budget
- As a user, when I select the quit option from the application menu, I want to be reminded to save configuration list to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my configuration list from file.


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
