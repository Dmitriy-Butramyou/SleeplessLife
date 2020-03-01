<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0b245d;">
    <a class="navbar-brand" href="/">SleeplessLife</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/user/list">userList</a>
            </li>
        </ul>
    </div>

    <form action="/logout" method="post">
        <button class="btn btn-outline-light" type="submit">Sign Out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>

</nav>