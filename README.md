### Project Management System

Made with Java 17 in IntelliJ 2024

##### Libraries used:
- FlatLaf for look and feel
- MigLayout for layout management
- Password4j for password hashing

##### Current Features:
- Authentication
- Project Management (Create, Update, Delete)
- Task Management (Create, Update, Delete)
- Searching (By Project Name)
- Report Generation
- Dashboard

##### Bugfixs:
- Project views show scroll bar on first open, following are not (fixed - removed all components then re-initialize all child components)
- Task board scroll bar not showing (fixed - dynamically updating viewport whenever a task is added)
- Throwing ClassCastException sometimes in reloadAllProjects() @ProjectManager class (fixed - did all the data manipulation logic then updated ui)

##### Future Optimizations:
- Word wrap on task cards (currently only wraps for two lines)
