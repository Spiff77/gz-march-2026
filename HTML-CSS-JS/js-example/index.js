<!DOCTYPE html>
<html lang=“en”>
<head>
    <meta charset=“UTF-8">
    <meta name=“viewport” content=“width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script type=“text/javascript”>
        async function initTable(){
            const response  = await fetch(‘http://localhost:8081/artists/’)
            const albums = await response.json()

            const tbody = document.querySelector(‘tbody’)

            for(let album of albums) {
                tbody.innerHTML += `<tr>`
                    tbody.innerHTML += `    <td>${album.id}</td><td>${album.name}</td><td>${album.country}</td>`
                    tbody.innerHTML += `</tr>`
            }
        }

    </script>
</head>
<body>
    <h1>Hello to my favorite artist list</h1>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Country</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
    <button onclick=“initTable()“>Load data from backend</button>
</body>
</html>
