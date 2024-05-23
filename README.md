### Project Management System

##### Current Features:
- Authentication
- Project Management (Create, Update, Delete)
- Task Management (Create, Update, Delete)
- Searching (By Project Name)

##### To Do Features:
- Report Generation
- Dashboard(if possible)

##### To Fix:
- Username validation (must be unique)

##### Bugs:
- Project views show scroll bar on first open, following are not (fixed - removed all components then re-initialize all child components)
- Task board scroll bar not showing (fixed - dynamically updating viewport whenever a task is added)
- Throwing ClassCastException sometimes in reloadAllProjects() @ProjectManager class (fixed - did all the data manipulation logic then updated ui)

##### Future Optimizations:
- Word wrap on task cards (currently only wraps for two lines)
- Project list is not sorted (could be fixed by comparator to sort in the getAllProjects method)
- Replace request/result system
