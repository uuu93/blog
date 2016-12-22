var flag = true; 
// 生成和价格库存有关的表格
function showPriceAndStock(e) {
	var price = $("#price").val();		//价格
	var stock = $("#stock").val();		//库存
	if(!stock){
		stock =0;
	}
	var htmlStr = '';
	$("#specchecktable").empty();
	var mulAttrArray = $("#spectable :checkbox:checked");
	var selectObj = {};
	var i=0;
	$.each(mulAttrArray,function(index,e){
		if(!selectObj[$(e).attr("class")]){
			selectObj[$(e).attr("class")] = $(e).attr("class");
			i++;
		}
	});
	if(i ==0){
		$("#specchecktable").empty();
		return false;
	}
	if(i >=3){
		alert('最多只能选择两种和商品的价格和库存有关的属性');
		flag = false;
		return false;
	}else if(i==1){
		htmlStr += '<thead><tr><td >' + mulAttrArray[0].name.split('|')[1]
				+ '</td><td>价格</td><td >库存</td></tr></thead>';
		$.each(mulAttrArray,function(index,e){
			$.each(existSpec,function(existindex,existarr){
				if(existarr.attr1value == e.value){
					price = existarr.price;
					stock = existarr.stock;
				}
			});
			htmlStr += '<tr><td>'+e.value+'</td>';
			htmlStr += '<td><input id="price'+e.id+'" class="Input_100 valid myvalid" myvaltype="money" myvalmsg="请输入金额"  type="number" min="0" max="100000000" name="proSpec.'+e.id+'|'+e.value+'|'+e.name+'|price" value="'+price+'"/></td>';
			htmlStr += '<td><input id="stock'+e.id+'" class="Input_100 valid myvalid" myvaltype="intGreater0" myvalmsg="请输入大于0的整数" type="number" min="0" max="100000000" name="proSpec.'+e.id+'|'+e.value+'|'+e.name+'|stock" value="'+stock+'"/></td>';
			htmlStr += '</tr>';
		});
	}else{
		var spec1Arr = $('.proSpec1:checkbox:checked');
		var spec2Arr = $('.proSpec2:checkbox:checked');
		htmlStr += '<thead><tr><td >' + spec1Arr[0].name.split('|')[1]
		+ '</td><td >' + spec2Arr[0].name.split('|')[1]
		+ '</td><td>价格</td><td >库存</td></tr></thead>';
		$.each(spec1Arr,function(index1,e1){
			$.each(spec2Arr,function(index2,e2){
				$.each(existSpec,function(existindex,existarr){
					if(existarr.attr1value == e1.value && existarr.attr2value == e2.value){
						price = existarr.price;
						stock = existarr.stock;
					}
				});
				if(index2 ==0){
					htmlStr += '<tr><td rowspan="'+spec2Arr.length+'">'+e1.value+'</td><td>'+e2.value+'</td>';
					htmlStr += '<td><input class="Input_100 valid myvalid" myvaltype="money" myvalmsg="请输入金额" type="number" min="0" max="100000000" name="proSpec.'+e1.id+'|'+e1.value+'|'+e1.name+'|'+e2.id+'|'+e2.value+'|'+e2.name+'|price" value="'+price+'"/></td>';
					htmlStr += '<td><input class="Input_100 valid myvalid" myvaltype="intGreater0" myvalmsg="请输入大于0的整数" type="number" min="0" max="100000000" name="proSpec.'+e1.id+'|'+e1.value+'|'+e1.name+'|'+e2.id+'|'+e2.value+'|'+e2.name+'|stock" value="'+stock+'"/></td></tr>';
				}else{
					htmlStr += '<tr><td>'+e2.value+'</td>';
					htmlStr += '<td><input class="Input_100 valid myvalid" myvaltype="money" myvalmsg="请输入金额"  type="number" min="0" max="100000000" name="proSpec.'+e1.id+'|'+e1.value+'|'+e1.name+'|'+e2.id+'|'+e2.value+'|'+e2.name+'|price" value="'+price+'"/></td>';
					htmlStr += '<td><input class="Input_100 valid myvalid" myvaltype="intGreater0" myvalmsg="请输入大于0的整数" type="number" min="0" max="100000000" name="proSpec.'+e1.id+'|'+e1.value+'|'+e1.name+'|'+e2.id+'|'+e2.value+'|'+e2.name+'|stock" value="'+stock+'"/></td></tr>';
				}
			});
		});
	}
	$('#specchecktable').html(htmlStr);
	
	countStock();
	$("input[name$='stock']").on('change',function(e){
		countStock();
	});
}

//计算库存
function countStock(){
	var stock = 0;
	$.each($("input[name$='stock']:input[name^='proSpec']"),function(index,e){
		if(!isNaN(e.value)){
			stock += parseInt(e.value);
		}
	});
	$("#stock").val(stock);
}