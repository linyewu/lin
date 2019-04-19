<template>
  <div class="home_main">
			
			<el-container>
				
				
			  <el-header style="height: 400px;">
			  	<el-carousel :interval="4000" type="card" height="350px">
					<el-carousel-item v-for="item in 6" :key="item">
					  <h3>{{ item }}</h3>
					</el-carousel-item>
				  </el-carousel>
			  </el-header>
	  <el-main>
		  <el-row>
			  <el-col :span="3"><span style="color: #C0C0C0;font-size: 20px;margin-left: 18px;">设备监测情况:</span></el-col>
		  
		  <el-col :span="2" class="logout-desc" >
		  	<el-badge :value="this.allCount" class="item">
		  	<el-button size="small" @click="open">人员不合格</el-button>
		  	</el-badge>
		  </el-col>
		  
		  </el-row>
		  <hr />
		  <el-row type="flex" >
		  	<span style="margin-left: 30px;font-weight: bold;padding-top: 8px;margin-right: 50px;">公告动态</span>
		  	
		  	<el-col :span="8">
		  		<!-- 查询 -->
		  		<el-input
		  			placeholder="请输入搜索内容"
		  			v-model="searchText"
		  			class="input-with-select">
		  			<el-button
		  				slot="append"
		  				icon="el-icon-search"
		  				@click="searchOlds"></el-button>
		  		</el-input>
		  	</el-col>
			
			
		  </el-row>
		  
		  <!--表格-->
		  <el-table
		  :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
		  @row-click="getDetail"
		  style="width: 100%">
		  <el-table-column
		  label=" "
		  	type="index">
		  </el-table-column>
		  <el-table-column
		  	label=" "
		  	width="180"
		  	prop="title">
		  </el-table-column>
		  <el-table-column
		  	label=""
		  	width="180"
		  	prop="time">
		  </el-table-column>
		  
		  <el-table-column label="">
		  	
		  </el-table-column>
		  </el-table>
		  <!-- start 分页 -->
		  <div style="margin-top: 10px;text-align: center">
		    <el-pagination
		      @size-change="handleSizeChange"
		      @current-change="changePage"
		      :current-page.sync="currentPage"
		      :page-size="pageSize"
		      layout="total, sizes, prev, pager, next, jumper"
		      :total="total">
		    </el-pagination>
		  </div>
		  <!-- end 分页 -->
	  </el-main>
	</el-container>
  </div>
</template>
<style scoped>
	.time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
  
  /* 轮播图 */
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>
<script>
	
	import axios from 'axios'
	
export default {
	created () {
		// let myChart = this.$echarts.init(document.getElementById('myChart'))
		console.log('---')
		this.searchOlds()
		this.getCount()
		console.log('---')
	// this.drawLine()
	},
	methods: {
      handleEdit(index, row) {
				
        console.log(index, row);
				 // this.$router.push({path: '/text'})
				var nametwo = row.title
				var textMain = row.textmain
				console.log('-------------------------------')
				console.log(nametwo+'----'+textMain)
				this.$router.push({
					path: '/text',
					query: {
						nametwo: nametwo,
						textMain: textMain
					}
				})
      },
	  open(){
		  
	  },
	  getCount(){
		  console.log('kaishe')
		  var _this=this
		  axios.post('step/getAllCount',
		  	{
		  		
		  	},
		  	{
		  		headers: {
		  			'content-type': 'application/json'
		  		},
		  		withCredentials: true
		  	}).then(function (response) {
		  	_this.allCount=response.data.allCount
		  })
		  	.catch(function (error) {
		  		console.log(error)
		  	})
	  },
	  handleSizeChange (val) {
	    console.log(`每页 ${val} 条`)
	    this.pageSize = val
	  },
	  handleCurrentChange (val) {
	    console.log(`当前页: ${val}`)
	    this.currentPage = val
	  },
	  changePage (val) {
	    console.log(`当前页: ${val}`)
	    this.currentPage = val
	  },
	  getDetail(row, column, event){
		  console.log(row, column, event)
		  var textId = row.textId
		  var picId = row.picId
		  console.log('-------------------------------')
		  this.$router.push({
		  	path: '/textMain',
		  	query: {
		  		textId: textId,
		  		picId: picId
		  	}
		  })
	  },
      handleDelete(index, row) {
        console.log(index, row);
      },
			addtext(){
				this.$router.push('/text')
			},
			searchOlds(){
				var _this=this
				
				var params = {
					'title': _this.searchText
				}
				console.log(params)
				axios.post('text/select',params,
				{
					headers: {
						'content-type': 'application/json'
					},
					withCredentials: true
				}).then(function (response) {
				console.log(response)
				_this.tableData=response.data.texts
				})
				.catch(function (error) {
					console.log(error)
				})
			}
			
			
    },
  data() {
    return {
	  tableData: [{
	  	time: '2016-05-02',
	  	title: '王小虎',
	  	textmain: '上海市普陀区金沙江路 1518 弄'
	  }, {
	  	time: '2016-05-04',
	  	title: '王小高',
	  	textmain: '上海市普陀区金沙江路 1517 弄'
	  }],
		searchText: '', // 查询内容
		total: 0,
		pageSize: 10,
		currentPage: 1,
		allCount:''
    };
  }
}
</script>