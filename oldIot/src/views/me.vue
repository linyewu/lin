<template>
  <div class="home_main">
			
	<el-container>
		<el-header><span style="color: black;font-size: 30px;">个人资料</span><br /><br /><br /></el-header>
		  <el-container>
			<el-aside style="widows: 110px;height: 300px;">
				<!-- <div style="width: 110px;height: 110px; "> -->
					<img style="width: 100px;height: 100px;border-radius: 50%;" :src="imageUrl" />
				<!-- </div> -->
				<!-- <img style="width: 100px;height: 100px;border-radius: 50%;margin-bottom: 60px;" v-if="imageUrl" :src="imageUrl" class="avatar">
				<i style="width: 100px;height: 100px;border-radius: 50%;margin-bottom: 60px;" v-else class="el-icon-plus avatar-uploader-icon"></i> -->
				<el-upload
				  class="avatar-uploader"
				  action="image/saveImage"
				  :show-file-list="false"
				  :on-success="handleAvatarSuccess"
				  :before-upload="beforeAvatarUpload">
				  <el-button type="primary">修改头像</el-button>
				</el-upload>
			</el-aside>
			<el-main>
				<span style="float: left;color: #999999;font-size: 30px;">Id:</span><br /><br /><br /><hr />
				<span style="float: left;color: black;font-size: 20px;">姓名:</span><el-button style="float: right;margin-right: 20px;" type="primary">修改资料</el-button><el-button style="float: right;margin-right: 20px;" type="primary">修改密码</el-button><br /><br /><br />
				<span style="float: left;color: black;font-size: 20px;">性别:</span><br /><br /><br />
				<span style="float: left;color: black;font-size: 20px;">地址:</span><br /><br /><br />
				<span style="float: left;color: black;font-size: 20px;">电话:</span><br /><br /><br />
			</el-main>
		  </el-container>	
	</el-container>
  </div>
</template>
<style scoped>
	.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
<script>
	
	import axios from 'axios'
	
export default {
	data() {
      return {
        imageUrl: require('../assets/img/QQ图片20170101150550.jpg')
      };
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
		console.log('--------')
		console.log(this.imageUrl)
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    }
  }
</script>
