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
                  <a class="nav-link" href="cari.php">PENCARIAN EDITOR</a>
                </li>
                <li class="nav-item active">
                  <a class="nav-link" href="tampil.php">TAMPILKAN EDITOR</a>
                </li>
                <li class="nav-item active">
                  <a class="nav-link" href="about.php">ABOUT ME</a>
                </li>
              </ul>
            </div>
        </div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="input-group mb-3 mt-3 w-50">
					<input aria-describedby="button-addon2" aria-label="Recipient's username" class="form-control input" placeholder="Nama Editor" type="text">
						<div class="input-group-append">
							<button class="btn btn-primary search text-white" id="button-addon2" type="button">
								<span class="fas fa-search fa-sm">Cari</span>
							</button>
						</div>
					</>
				</div>
				<hr class="my-4">
				</hr>
				<div class="result text-center">
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col">
				<div class="result d-flex justify-content-center mt-4"></div>
			</div>
		</div>
	</div>

	<script src="assets\bootstrap\js\jquery.min.js"></script>
	<script src="assets\bootstrap\js\bootstrap.min.js"></script>
	
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
</body>
</html>