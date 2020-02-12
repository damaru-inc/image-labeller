# image-labeller

This takes a base64-encoded image and returns a list of labels from the Google Vision API.

You need a Google Vision API key to use it. The path to the key file must be set as an environment variable, e.g.

GOOGLE_APPLICATION_CREDENTIALS=/Users/fred/creds/api-b753304c8ea7.json

Suppose you have an image of my dog, picture.jpg, and you have this app running locally. You can fetch the labels by running:

base64 -i picture.jpg -o picture.encoded

curl -H "Content-Type: text/plain" -d @picture.encoded localhost:8080/labels

The output would be:

```json
[
  {
    "description": "Dog",
    "score": 0.9953705
  },
  {
    "description": "Mammal",
    "score": 0.9890478
  },
  {
    "description": "Vertebrate",
    "score": 0.9851104
  },
  {
    "description": "Canidae",
    "score": 0.981378
  },
  {
    "description": "Dog breed",
    "score": 0.9584342
  },
  {
    "description": "Companion dog",
    "score": 0.8877833
  },
  {
    "description": "Maltese",
    "score": 0.85163915
  },
  {
    "description": "Carnivore",
    "score": 0.84494114
  },
  {
    "description": "Bichon",
    "score": 0.78676623
  },
  {
    "description": "Bolognese",
    "score": 0.76107883
  }
]
```
