<header>
    <section>
        <img src="images/7d041c7af4c707962900e6ab49608932.png" alt="popcorn logo">
        <h1>Movie center</h1>
    </section>
    <nav>
        <ul class="menu">
            <li ${param.current eq 'Home' ?"id= current": ""}><a href="Controller">Home</a></li>
            <li ${param.current eq 'Overzicht' ?"id= current": ""} > <a href="Controller?command=overzicht">Overzicht</a></li>
            <li ${param.current eq 'Voegtoe' ?"id= current": ""}><a href="Controller?command=voegtoeForm">Voeg toe</a></li>
            <li ${param.current eq 'Zoeken' ?"id= current": ""}><a href="Controller?command=zoekenpagina">Zoeken</a></li>
            <li ${param.current eq 'Journal' ?"id= current": ""}><a href="Controller?command=journal">Journal</a></li>
        </ul>
    </nav>

</header>

