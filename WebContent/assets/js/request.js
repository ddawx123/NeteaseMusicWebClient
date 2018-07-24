/**
 * 云音乐前端逻辑
 */

function searchAction() {
	if ($('#keyword').val() == "") {
		alert('亲，关键词不能为空哦~');
		return false;
	}
	$.ajax({
		url: './api?action=search&keyword=' + $('#keyword').val(),
		method: 'get',
		dataType: 'json',
		async: true,
		success: function (res) {
			if (res.code == 401) {
				alert('未登录，请前往登陆页面完成身份认证。');
				location.href = "./login";
				return false;
			}
			$('#tableView').show();
			var html;
			$('#mlist').html('');
			$.each(res.result.songs, function (key, value) {
				var artists = '';
				$.each(value.artists, function (key, value) {
					if (key == 0) {
						artists += value.name;
					}
					else {
						artists += ',' +value.name;
					}
				});
				html += '<tr><th>' + (key + 1) + '</th><th>' + value.name + '</th><th>' + artists + '</th><th>' + value.album.name + '</th><th>' + '<a id="t_' + value.id + '" href="javascript:void(0);" target="_self" onclick="playMusic(\'' + value.id + '\')">【试听】</a> | <a href="javascript:void(0);" target="_self" onclick="downloadMusic(\'' + value.id + '\')">【下载】</a>' + '</th></td>';
				$('#dataCount').html((key + 1) + ' 条记录');
			});
			$('#mlist').html(html);
		},
		error: function (e) {
			alert('网络异常，请稍后刷新页面重试。');
		}
	});
}

function playMusic(id) {
	$.ajax({
		url: './api?action=resource&rid=' + id,
		method: 'get',
		dataType: 'json',
		async: true,
		success: function (res) {
			if (res.code == 401) {
				alert('未登录，请前往登陆页面完成身份认证。');
				location.href = "./login";
				return false;
			}
			else if (res.data.subcode == 200) {
				$('#aplayer')[0].src = res.data.url;
				$('#aplayer')[0].play();
				$('#t_' + id).html('【暂停】');
				$('#t_' + id).attr('onclick', 'pauseMusic("' + id + '")');
			}
			else {
				alert('由于版权问题，该歌曲暂时无法播放。');
			}
		},
		error: function (e) {
			alert('网络异常，请稍后刷新页面重试。');
		}
	});
}

function pauseMusic(id) {
	$('#aplayer')[0].pause();
	$('#t_' + id).html('【试听】');
	$('#t_' + id).attr('onclick', 'playMusic("' + id + '")');
}

function downloadMusic(id) {
	$.ajax({
		url: './api?action=resource&rid=' + id,
		method: 'get',
		dataType: 'json',
		async: true,
		success: function (res) {
			if (res.code == 401) {
				alert('未登录，请前往登陆页面完成身份认证。');
				location.href = "./login";
				return false;
			}
			else if (res.data.subcode == 200) {
				window.open(res.data.url);
			}
			else {
				alert('由于版权问题，该歌曲暂时无法播放。');
			}
		},
		error: function (e) {
			alert('网络异常，请稍后刷新页面重试。');
		}
	});
}