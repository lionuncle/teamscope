<div align="center">
	<h1> 🦉 Teamscope-sample-app<br>
	</h1>
</div>

> A form creating, filling and management application built with latest dependencies and best code practices

## 🏗 STRUCTURE

The project follows MVVM design pattern.
![img project structure](structure.PNG "PROJECT STRUCTURE")

### -Repository
The repository is responsible for fetching and storing data. Since this project uses Firebase as a backend, it gets and sets data to it and has been implemented with singleton design pattern. So, there will be only 1 instance created

### -ViewModel
The viewmodels are lifecycle aware data manipulation classes. They provide data to the view.

### -Views
The views hold activities and fragments. There is no business logic in views. Their single responsibility is to show data fetched from view models

![navigation](navigation.PNG "")


## 👨🏻‍💻 AUTHOR

Howdy, you! This is [Muhammad Aqib](http://aqib.engineer/), an aspiring Android Developer. Connect with me:

<div>
<a href="https://twitter.com/_lionuncle">
  <img align="left" alt="M AQIB | Twitter" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/twitter.svg" />
</a>
<a href="https://www.linkedin.com/in/lionuncle/">
  <img align="left" alt="Saad's LinkdeIN" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/linkedin.svg" />
</a>
<a href="https://www.facebook.com/Lionuncle/">
  <img align="left" alt="Saad's Facebook" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/facebook.svg" />
</a>
</div>
<br>

## 🔑 LICENSE

- MIT
