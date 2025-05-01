import os
import sys
import requests

# Récupération des paramètres depuis les arguments
try:
    username = sys.argv[1]
    password = sys.argv[2]
except IndexError:
    print("Usage: python script.py <username> <password>")
    sys.exit(1)

# Définition de l'adresse de l'API
api_address = os.environ.get('API_ADDRESS', '')
api_port = os.environ.get('API_PORT', 8000)
api_path = os.environ.get('API_PATH', 'v1/sentiment')
# Requête
response = requests.get(
    url=f'http://{api_address}:{api_port}/{api_path}',
    params={
        'username': username,
        'password': password,
    }
)

output=""
# Affichage de la réponse
if response.status_code == 200:
    try:
        result = response.json()  # Extraction correcte des données JSON

        output = f'''
        ============================
                Sentiment Analysis
        ============================

        Username: {result.get("username")}
        Password:  {result.get("password")}
        Sentence: "{result.get("sentence")}"
        Score:    {result.get("score")}

        '''
        print(output)
    except Exception as e:
        print(f"Error while processing the response JSON: {e}")

else:
    output = f"Error: API call failed with status code {response.status_code}\n"
    print(output)

# Enregistrement dans les logs si nécessaire
if os.environ.get('LOG') == '1':
    with open('/app/logs/api_test.log', 'a') as file:
        file.write(output)
