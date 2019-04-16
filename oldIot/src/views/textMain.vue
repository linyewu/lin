<template>
	<div>
		<div style="font-size: 30px;font-weight: bold;text-align: center;">{{title}}</div>
		<p style="text-align: center;">{{time}}</p>
		<p>{{textMain}}</p>
		<div style="width: 550px;height: 600px;margin-left: 400px;">
			<el-carousel indicator-position="outside" >
				<el-carousel-item v-for="(img,index) in imgList" :key="index">
				  <img v-bind:src="img.url">
				</el-carousel-item>
			</el-carousel>
		</div>
		<div>
			<el-row>
				<div style="width: 40px;height: 40px; position: absolute;">
					<img style="width: 40px;height: 40px;border-radius: 50%;position: relative;top: 6px;left: 21px;" src="../assets/img/QQ图片20170101150550.jpg" />
				</div>
				<div>
					<el-form ref="formSaid" :model="formSaid" label-width="80px">
					<el-form-item label="">
						<el-input type="textarea" v-model="formSaid.said" placeholder="留言板"></el-input>
					  </el-form-item>
					  <el-form-item>
						<el-button type="primary" @click="onSubmit" style="float: right;">发表</el-button>
					  </el-form-item>
					  </el-form>
				</div>
			</el-row>
		</div>
		
		<div>
					<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
			label=" "
				type="index">
			</el-table-column>
			<el-table-column
				label=" "
				width="50"
				prop="title">
				<template slot-scope="scope">
					<div style="width: 40px;height: 40px; ">
						<img style="width: 40px;height: 40px;border-radius: 50%;" src="../assets/img/QQ图片20170101150550.jpg" />
					</div>
				          <!-- <span v-if="scope.row.preState==1" style="color:red">异常</span>
				          <span v-else style="color: #37B328">正常</span> -->
				</template>
			</el-table-column>
			<el-table-column
				label=""
				width="100"
				prop="title">
			</el-table-column>
			<el-table-column
				label=""
				width="180"
				prop="time">
			</el-table-column>
			
			
			<el-table-column label=""
				width="400"
				prop="time">
				<template slot-scope="scope">
					<!-- <span style="float: right;">赞</span> -->
					<span v-if="good==0" style="color:red;float: right;">赞</span>
					<span v-else style="color: #37B328;float: right;">赞</span>
				</template>
			</el-table-column>
			</el-table>
		</div>
	</div>
</template>

<script>
	
	import axios from 'axios'
	
	export default {
		created(){
			console.log('==============' + 'created' + '===================')
			this.picId=this.$route.query.picId
			this.textId=this.$route.query.textId
			console.log(this.picId+'55555 '+this.textId)
			this.searchDetail()
			// console.log(this.textform)
			// console.log(this.textform.name+'------'+this.$route.query.textMain)
		},
		data() {
			return {
				title: '',
				textId: '',
				picId: '',
				time: '',
				textMain: '',
				formSaid: {
					said: ''
				},
				tableData: [{
				time: '2016-05-02',
				title: '王小虎',
				textmain: '上海市普陀区金沙江路 1518 弄'
				}, {
				time: '2016-05-04',
				title: '王小高',
				textmain: '上海市普陀区金沙江路 1517 弄'
				}],
				imgList:[
// 				{url:require('../assets/img/1.jpg')},
// 				{url:require('../assets/img/2.jpg')},
// 				{url:require('../assets/img/image.jpg')}
			  ],
			  good:'1'
			};
		},
		methods: {
			searchDetail(){
				console.log(this.picId+'55555 '+this.textId)
				var _this=this
				
				var params = {
					'textId': this.textId
				}
				console.log(params)
				axios.post('text/selectDetail',params,
				{
					headers: {
						'content-type': 'application/json'
					},
					withCredentials: true
				}).then(function (response) {
				console.log(response)
				_this.title=response.data.text.title
				_this.time=response.data.text.time
				_this.textMain=response.data.text.textMain
				var pics=response.data.pics
				_this.imgList=response.data.pics
				for(var i=0;i<_this.imgList.length;i++){
					console.log(_this.imgList[i].url)
					_this.imgList[i].url = require('../assets/img/'+pics[i].picName)
				}
				
				
				})
				.catch(function (error) {
					console.log(error)
				})
			},
			onSubmit(){
				
			}
			
		}
	}
</script>

<style>

</style>
