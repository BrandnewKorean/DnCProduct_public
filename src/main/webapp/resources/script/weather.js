$(function(){
	var background = document.getElementById('background');
	var wCanvas = document.getElementById('w_canvas');
	var ctx = wCanvas.getContext('2d');
		
	background.width = window.innerWidth;
	background.height = window.innerHeight * 0.7;
	
	wCanvas.width = background.width;
	wCanvas.height = background.height;
	
	var width = wCanvas.width;
	var height = wCanvas.height;
	
	$(window).resize(function() {
		background.width = window.innerWidth;
		background.height = window.innerHeight * 0.7;
		
		wCanvas.width = background.width;
		wCanvas.height = background.height;
	
		width = wCanvas.width;
		height = wCanvas.height;
	});
//	var select = prompt("선택 : 1.눈 2.비");
	var select = 2;
	var maxparts = 100;
	var parts = [];
	var angle = 0;
	
	for(var i=0;i<maxparts;i++){
		parts.push({
			x: Math.random()*width,
			y: Math.random()*height,
			r: Math.random()*10+1,
			d: Math.random()*maxparts
		});
	}
	
	function draw()
	{
		ctx.clearRect(0, 0, width, height);
		
		if(select == 1){
			ctx.fillStyle = "rgba(255,255,255,0.8)";
		}else{
			ctx.strokeStyle = "rgba(255,255,255,0.8)";
			ctx.lineWidth = 1;
		}

		ctx.beginPath();
		for(var i = 0; i < maxparts; i++)
		{
			var p = parts[i];
			ctx.moveTo(p.x, p.y);
			if(select == 1){
				ctx.arc(p.x, p.y, p.r, 0, Math.PI*2, true);
			}else{
				ctx.lineTo(p.x, p.y+(p.r*4));
			}
		}
		if(select == 1){
			ctx.fill();
		}else{
			ctx.stroke();
		}
		update();
	}

	function update()
	{
		angle += 0.01;
		for(var i = 0; i < maxparts; i++)
		{
			var p = parts[i];
			if(select == 1){
				p.y += Math.cos(angle+p.d) + 1 + p.r/2;
				p.x += Math.sin(angle) * 2;
			}else{
				p.y += 15;
			}
			
			if(p.x > width+5 || p.x < -5 || p.y > height)
			{
				if(i%3 > 0)
				{
					parts[i] = {x: Math.random()*width, y: -10, r: p.r, d: p.d};
				}
				else
				{
					if(Math.sin(angle) > 0)
					{
						parts[i] = {x: -5, y: Math.random()*height, r: p.r, d: p.d};
					}
					else
					{
						parts[i] = {x: width+5, y: Math.random()*height, r: p.r, d: p.d};
					}
				}
			}
		}
	}
	
	setInterval(draw, 35);
});