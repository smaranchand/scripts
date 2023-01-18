#This script will delete all domains in your Mailgun account, including all associated data.
#I created this script to help me clean up  Mailgun account after testing mx-takeover tool developed by @musana
# https://github.com/musana/mx-takeover
#Usage: python3 mailgun_delete_domains.py <API_KEY>
import sys
import requests

if len(sys.argv) < 2:
    print("Error: No API key provided. Please provide the API key as an argument.")
    sys.exit(1)

api_key = sys.argv[1]

url = "https://api.mailgun.net/v3/domains"

response = requests.get(url, auth=("api", api_key), params={"limit": 500})

data = response.json()["items"]

for item in data:
    name = item["name"]
    print(f"Deleting {name}")
    delete_url = f"https://api.mailgun.net/v3/domains/{name}"
    requests.delete(delete_url, auth=("api", api_key))
