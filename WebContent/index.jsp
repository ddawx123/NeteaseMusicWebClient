<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<title>叮云音乐H5版</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
	</head>
	<body class="landing is-preload">
		<div id="page-wrapper">
			<!-- Header -->
				<header id="header" class="alt">
					<h1><a href="index.html">Cloud Music</a> by DingStudio</h1>
					<nav id="nav">
						<ul>
							<li><a href="index.jsp">Home</a></li>
						</ul>
					</nav>
				</header>
			<!-- Banner -->
				<section id="banner">
					<h2>Cloud Music</h2>
					<p>Resource provider: Netease Music.</p>
				</section>
			<!-- Main -->
				<section id="main" class="container">
					<section class="box special features">
						<div class="features-row">
						<div id="mbox" class="row gtr-50 gtr-uniform">
							<div class="col-8 col-12-mobilep">
								<input type="text" name="keyword" id="keyword" placeholder="键入歌曲关键词 Search For Your Keywords" />
							</div>
							<div class="col-4 col-12-mobilep">
								<input type="button" value="点此搜索" class="fit" onclick="searchAction()" />
							</div>
							
							<div id="tableView" class="table-wrapper" style="display: none">
								<table>
									<thead>
										<tr>
											<th>输出序号</th>
											<th>歌曲名称</th>
											<th>歌曲作者</th>
											<th>专辑名称</th>
											<th>播放/下载</th>
										</tr>
									</thead>
									<tbody id="mlist">
									</tbody>
									<tfoot>
										<tr>
											<td colspan="4">查询结果数：</td>
											<td id="dataCount" colspan="1">0 条记录</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
						</div>
					</section>
				</section>
				<div style="display: none"><audio id="aplayer" controls="controls"></audio></div>
			<!-- Footer -->
				<footer id="footer">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
						<li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
						<li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
					</ul>
					<ul class="copyright">
						<li>&copy; 2012-2018 DingStudio Technology All rights reserved.</li><li>Design: <a href="http://www.dingstudio.cn">DingStudio</a></li>
					</ul>
				</footer>
		</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/jquery.scrollex.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script>
			<script src="assets/js/request.js"></script>

	</body>
</html>