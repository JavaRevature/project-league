import json
import csv

# Load the JSON data from the file with explicit encoding (e.g., UTF-8)
json_file = 'champion.json'
with open(json_file, 'r', encoding='utf-8') as file:
    champion_data = json.load(file)

# Access the champions under the "data" key
champions = champion_data["data"]

# Define CSV file path for the output
csv_file = 'champion.csv'

# Write champion data to CSV
with open(csv_file, mode='w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["Champion Name", "Attack", "Defense", "Magic", "Difficulty"])  # Write header

    # Write champion information
    for champion, details in champions.items():
        info = details["info"]
        writer.writerow(['(' + '\'' + champion + '\'' , str(info["attack"]), str(info["defense"]),str(info["magic"]),str(info["difficulty"])+')'])

print(f"Champion information has been written to {csv_file}")
