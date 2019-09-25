<template>
	<div>
		<el-button type="primary" style="float: right;" @click="logout">退出登录</el-button>
		<h1 style="text-align: center;">用户信息管理界面</h1>
		<el-row>
		        <el-col :span="20" :push='2'>
		            <div>
		                <el-form :inline="true">
		                    <el-form-item style="float: left" label="查询用户信息:">
		                        <el-input v-model="keyUser" placeholder="查询所需要的内容......"></el-input>
		                    </el-form-item>
		                    <el-form-item style="float: right">
		                        <el-button type="primary" size="small" icon="el-icon-edit-outline" @click="dialogAdd.show = true">添加</el-button>
		                    </el-form-item>
		                </el-form>
		            </div>
		            <div class="table">
		                <el-table
		                    :data="searchUserinfo(keyUser)"
		                    border
		                    style="width: 100%">
		                    <el-table-column
		                      type="index"
		                      label="序号"
		                      align="center"
		                      width="60">
		                    </el-table-column>
		                    <el-table-column
		                      label="日期"
		                      align="center"
		                      width="120">
		                      <template slot-scope="scope">
		                        <span>{{ scope.row.date | moment}}</span>
		                      </template>
		                    </el-table-column>
		                    <el-table-column
		                      label="姓名"
							  prop="name"
		                      align="center"
		                      width="100">
		                    </el-table-column>
		                    <el-table-column
		                      label="邮箱"
							  prop="email"
		                      align="center"
		                      width="160">
		                    </el-table-column>
		                    <el-table-column
		                      label="标题"
							  prop="title"
		                      align="center"
		                      width="160">
		                    </el-table-column>
		                    <el-table-column
		                      label="评价"
							  prop="evaluate"
		                      align="center"
		                      width="200">
		                    </el-table-column>
		                    <el-table-column
		                      label="状态"
							  prop="state"
		                      align="center"
		                      width="160">
		                    </el-table-column>
		                    <el-table-column label="操作" align="center">
		                      <template slot-scope="scope">
		                        <el-button
		                          size="mini"
		                          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
		                        <el-button
		                          size="mini"
		                          type="danger"
		                          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
		                      </template>
		                    </el-table-column>
		                  </el-table>
		            </div>
		        </el-col>
		    </el-row>
			
			<el-dialog title="添加用户信息" :visible.sync="dialogAdd.show">
			    <el-form :model="formDate" ref="formdong" label-width="100px" :rules="formrules">
			        <el-form-item label="日期" prop="date">
			            <el-date-picker
			              v-model="formDate.date"
			              type="date"
			              placeholder="选择日期">
			            </el-date-picker>
			        </el-form-item>
			        <el-form-item label="姓名" prop="name">
			          <el-input v-model="formDate.name"></el-input>
			        </el-form-item>
			        <el-form-item label="邮箱" prop="email">
			          <el-input v-model="formDate.email"></el-input>
			        </el-form-item>
			        <el-form-item label="标题" prop="title">
			          <el-input v-model="formDate.title"></el-input>
			        </el-form-item>
			        <el-form-item label="评价" prop="evaluate">
			          <el-input v-model="formDate.evaluate"></el-input>
			        </el-form-item>
			        <el-form-item label="状态" prop="state">
			          <el-input v-model="formDate.state"></el-input>
			        </el-form-item>
			    </el-form>
			    <div slot="footer" class="dialog-footer">
			        <el-button @click="dialogAdd.show = false">取 消</el-button>
			        <el-button type="primary" @click="dialogFormAdd('formdong')">确 定</el-button>
				</div>
			</el-dialog>
			
			<el-dialog title="编辑用户信息" :visible.sync="dialogEdit.show">
			    <el-form :model="formDate" ref="formdong" label-width="100px" :rules="formrules">
			        <el-form-item label="日期" prop="date">
			            <el-date-picker
			              v-model="formDate.date"
			              type="date"
			              placeholder="选择日期">
			            </el-date-picker>
			        </el-form-item>
			        <el-form-item label="姓名" prop="name">
			          <el-input v-model="formDate.name"></el-input>
			        </el-form-item>
			        <el-form-item label="邮箱" prop="email">
			          <el-input v-model="formDate.email"></el-input>
			        </el-form-item>
			        <el-form-item label="标题" prop="title">
			          <el-input v-model="formDate.title"></el-input>
			        </el-form-item>
			        <el-form-item label="评价" prop="evaluate">
			          <el-input v-model="formDate.evaluate"></el-input>
			        </el-form-item>
			        <el-form-item label="状态" prop="state">
			          <el-input v-model="formDate.state"></el-input>
			        </el-form-item>
			    </el-form>
			    <div slot="footer" class="dialog-footer">
			        <el-button @click="dialogEdit.show = false">取 消</el-button>
			        <el-button type="primary" @click="dialogFormEidt('formdong')">确 定</el-button>
				</div>
			</el-dialog>
	</div>
</template>

<style>
  .el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>

<script>
import { Loading } from 'element-ui';
  export default {
	name:'UserInfo',
	data() {
	  return {
	    tableData: [],
		keyUser: '',
		dialogAdd: {
			show: false
		},
		dialogEdit:{
			show: false
		},
		formDate:{
			name: '',
			email: '',
			title: '',
			evaluate: '',
			state: '',
			date: '',
			id: ''
		},
		formrules:{
			name: [{required: true, message: '姓名不能为空', trigger: 'blur'}],
			email: [{required: true, message: '邮箱不能为空', trigger: 'blur'}]
		}
	  }
	},
    methods: {
      tableRowClassName({row, rowIndex}) {
        if ((rowIndex % 2) === 0) {
          return 'warning-row';
        } else {
          return 'success-row';
        }
        return '';
      },
	  getInfo(){
		  let loadingInstance = Loading.service({ fullscreen: true });
		  this.$http
		  .post('/getInfo').then(response => {
				if(response.data.code == 200){
					this.tableData = response.data.data;
					loadingInstance.close();
				}
		  })
	  },
	  searchUserinfo(keyUser){
		  return this.tableData.filter((stu) => {
			  if(stu.name.includes(keyUser)){
				  return stu;
			  }
		  })
	  },
	  dialogFormAdd(formdong){
		  this.$refs[formdong].validate((valid) => {
			  if(valid){
				  this.$http.post('/saveInfo',this.formDate).then((response) => {
					  if(response.data.code ==200){
						  this.utils.alertMessage(this,'保存成功',true);
						  this.dialogAdd.show = false;
						  location.reload();
					  }else{
						  this.utils.alertMessage(this, response.data.desc, false);
					  }
				  })
			  } else{
				 this.utils.alertMessage(this,'请填写必要信息',false);
				 return;
			  }
		  })
	  },
	  handleDelete(index,row){
		  this.$http.post('/deleteById?id='+row.id).then((response) => {
			  this.utils.alertMessage(this,'删除成功',true);
			  location.reload();
		  })
	  },
	  handleEdit(index, row){
		  this.dialogEdit.show = true;
		  this.formDate = {
			  name: row.name,
			  date: row.date,
			  email: row.email,
			  evaluate: row.evaluate,
			  state: row.state,
			  title: row.title,
			  id: row.id
		  }
	  },
	  dialogFormEidt(formdong){
		  this.$refs[formdong].validate((valid) => {
			  if(valid){
				  this.$http.post('/update',this.formDate).then(response => {
					  if(response.data.code == 200){
						  this.dialogEdit.show = false;
						  this.utils.alertMessage(this,'修改成功', true);
						  location.reload();
					  } else {
						  this.dialogEdit.show = false;
						  this.utils.alertMessage(this, response.data.desc, true);
					  }
				  })
			  } else {
				  this.utils.alertMessage(this, '请填写必要信息', false);
				  return;
			  }
		  })
	  },
	  logout(){
		this.$store.commit('logout');
		this.utils.alertMessage(this,'退出登录成功',true);
		location.reload();
	  }
    },
	created() {
		this.getInfo();
	}
  }
</script>
