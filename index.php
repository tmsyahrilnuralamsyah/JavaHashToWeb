<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Data Editor</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #061C30;">
        <div class="container">
            <a class="navbar-brand font-weight-bold" href="index.php"><i class="fas fa-edit"></i> DATA EDITOR</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              	<span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active">
					<a class="nav-link" href="https://github.com/tmsyahrilnuralamsyah/JavaHashToWeb">GitHub</a>
					</li>
					<li class="nav-item active">
					<a class="nav-link" href="about.php">About Me</a>
					</li>
				</ul>
            </div>
        </div>
	</nav>
	<div class="container">
		<div class="text-center mt-3">
			<h5>Website ini menampilkan informasi editor pada artikel situs detik.com beserta artikel yang telah diedit
				<br>Data yang diperoleh berasal dari struktur data hash yang diintegrasi dengan web</h5>
		</div>
		<div class="row justify-content-end">
			<div class="col-lg-4">
				<div class="input-group my-3">
					<input aria-describedby="button-addon2" aria-label="Recipient's username" class="form-control input" placeholder="Cari..." type="text">
					<div class="input-group-append">
						<a class="btn btn-primary search text-white" id="button-addon2" type="button">
							<span class="fas fa-search fa-sm">Cari</span>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="result text-center"></div>
	</div>

	<script src="assets\js\jquery.min.js"></script>
	<script src="assets\js\bootstrap.min.js"></script>
</body>
</html>
<script>
	loadData();

	function loadData(){
		$.get('hash/get.php?q=show', function(data){
			$(".result").html(data);
		});
	}

	$(".search").click(function(){
		loadCari();
	});

	function loadCari(){
		$.get('hash/get.php?q="'+$(".input").val()+'"', function(data){
			$(".result").html(data);
		});
	}
</script>