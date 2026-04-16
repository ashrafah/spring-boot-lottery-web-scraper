Lottery Results Scraper (Spring Boot + Jsoup)

Overview

This project is a Spring Boot application that scrapes lottery results from a website using Jsoup and stores them in a MySQL database.

Technologies Used

• Java
• Spring Boot
• Spring Data JPA
• MySQL
• Jsoup
• Lombok

Features

• Fetch lottery results from website
• Extract Draw Date, Event Number, Winning Numbers, Next Jackpot
• Store data in MySQL database
• Runs automatically on application startup

Project Structure

entity → Database model
repo → JPA repository
service → Scraping logic
main → Application entry point

Setup Instructions

1. Clone repository
2. Configure database in application.properties
3. Run MySQL server
4. Run application using Maven or IDE
   
Database Table

Table: lotteryresult

Columns:
- eventNo (Primary Key)
- draw_date
- results
- next_jackpot

How It Works

Application starts → Scraper runs → Jsoup fetches data → Data is parsed → Saved to database
Notes

• Avoid duplicate entries (eventNo as primary key)
• Website structure changes may break scraper
• Internet connection required

Author

M.A.A.Ahamad – ashraf.ahamad503@gmail.com
