online_forum/
│
├── templates/
│   ├── layout.html
│   ├── home.html
│   ├── forum.html
│   └── post.html
│
├── static/
│   └── style.css
│
├── app.py
└── README.md







from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__)

# Dummy data for demonstration
posts = [
    {"author": "John", "title": "First post", "content": "This is the first post content."},
    {"author": "Alice", "title": "Second post", "content": "This is the second post content."}
]

@app.route("/")
@app.route("/home")
def home():
    return render_template("home.html", posts=posts)

@app.route("/forum")
def forum():
    return render_template("forum.html", posts=posts)

@app.route("/post/<int:post_id>")
def post(post_id):
    post = posts[post_id - 1]
    return render_template("post.html", post=post)

if __name__ == "__main__":
    app.run(debug=True)







<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Forum</title>
    <link rel="stylesheet" href="{{ url_for('static', filename='style.css') }}">
</head>
<body>
    <header>
        <h1>Online Forum</h1>
        <nav>
            <ul>
                <li><a href="{{ url_for('home') }}">Home</a></li>
                <li><a href="{{ url_for('forum') }}">Forum</a></li>
            </ul>
        </nav>
    </header>
    <main>
        {% block content %}{% endblock %}
    </main>
</body>
</html>







{% extends "layout.html" %}

{% block content %}
    <h2>Welcome to the Online Forum!</h2>
    <p>This is the home page.</p>
{% endblock %}







{% extends "layout.html" %}

{% block content %}
    <h2>Forum</h2>
    <ul>
    {% for post in posts %}
        <li><a href="{{ url_for('post', post_id=loop.index) }}">{{ post.title }}</a> by {{ post.author }}</li>
    {% endfor %}
    </ul>
{% endblock %}







{% extends "layout.html" %}

{% block content %}
    <article>
        <h2>{{ post.title }}</h2>
        <p>Author: {{ post.author }}</p>
        <p>{{ post.content }}</p>
    </article>
{% endblock %}







/* Add your CSS styles here */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

header {
    background-color: #333;
    color: #fff;
    padding: 1em 0;
    text-align: center;
}

nav ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
}

nav ul li {
    display: inline;
    margin-right: 1em;
}

nav ul li a {
    color: #fff;
    text-decoration: none;
}

main {
    padding: 1em;
}
