### TODO

CONVERTED TO ACTION-BASED:
- Authentication
- Project CRUD
- Task CRUD
- Searching (by project name)
- Edit project/task status

FUTURE:
- Report Generation
- Dashboard(if possible)

bugs:
- project views show scroll bar on first open, following are not (fixed - removed all components then re-initialize all child components)
- task board scroll bar not showing (fixed - dynamically updating viewport whenever a task is added)
- throwing ClassCastException sometimes in reloadAllProjects() @ProjectManager class (fixed - did all the data manipulation logic then updated ui)

future optimizations:
- word wrap on task cards (currently only wraps for two lines)
- project list is not sorted (could be fixed by comparator to sort in the getAllProjects method)
- replace request/result system
