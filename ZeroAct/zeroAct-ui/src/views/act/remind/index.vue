<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动ID" prop="actId">
        <el-input
          v-model="queryParams.actId"
          placeholder="请输入活动ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动名称" prop="actName">
        <el-input
          v-model="queryParams.actName"
          placeholder="请输入活动名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提醒时间" prop="remindTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.remindTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择提醒时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动链接" prop="actLink">
        <el-input
          v-model="queryParams.actLink"
          placeholder="请输入活动链接"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="打卡状态" prop="signFlag">
        <el-select v-model="queryParams.signFlag" placeholder="请选择打卡状态" clearable size="small">
          <el-option
            v-for="dict in signFlagOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="打卡时间" prop="signTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.signTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择打卡时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户昵称" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否开启提醒" prop="isMessage">
        <el-select v-model="queryParams.isMessage" placeholder="请选择是否开启提醒" clearable size="small">
          <el-option
            v-for="dict in isMessageOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['act:remind:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['act:remind:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['act:remind:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['act:remind:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="remindList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="活动ID" align="center" prop="actId" />
      <el-table-column label="主键" align="center" prop="remindId" />-->
      <el-table-column label="活动名称" align="center" prop="actName" />
      <el-table-column label="提醒时间" align="center" prop="remindTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.remindTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动链接" align="center" prop="actLink" />
      <el-table-column label="打卡状态" align="center" prop="signFlag" :formatter="signFlagFormat" />
      <el-table-column label="打卡时间" align="center" prop="signTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.signTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="用户ID" align="center" prop="userId" />-->
      <el-table-column label="用户昵称" align="center" prop="userName" />
<!--      <el-table-column label="是否开启提醒" align="center" prop="isMessage" :formatter="isMessageFormat" />
      <el-table-column label="备注" align="center" prop="remark" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="handleView(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['act:remind:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['act:remind:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />



  <!-- 详情 -->
<el-dialog title="详情" :visible.sync="xqopen" width="700px" append-to-body>
<el-form ref="form" :model="form" label-width="100px" size="mini">
<el-row>
  <el-col :span="24">
    <el-form-item label="活动ID：">{{form.actId }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="主键：">{{form.remindId }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="活动名称：">{{form.actName }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="提醒时间：">{{form.remindTime }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="活动链接：">{{form.actLink }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="打卡状态：">{{form.signFlag }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="打卡时间：">{{form.signTime }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="用户ID：">{{form.userId }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="用户昵称：">{{form.userName }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="是否开启提醒（预留字段）：">{{form.isMessage }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="删除标志（0代表存在 2代表删除）：">{{form.delFlag }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="创建者：">{{form.createBy }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="创建时间：">{{form.createTime }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="更新者：">{{form.updateBy }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="更新时间：">{{form.updateTime }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="备注：">{{form.remark }}</el-form-item>
  </el-col>
 </el-row>
</el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="xqopen = false">关 闭</el-button>
  </div>
</el-dialog>




    <!-- 添加或修改活动打卡记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动ID" prop="actId">
          <el-input v-model="form.actId" placeholder="请输入活动ID" />
        </el-form-item>
        <el-form-item label="活动名称" prop="actName">
          <el-input v-model="form.actName" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="提醒时间" prop="remindTime">
          <el-date-picker clearable size="small"
            v-model="form.remindTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择提醒时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动链接" prop="actLink">
          <el-input v-model="form.actLink" placeholder="请输入活动链接" />
        </el-form-item>
        <el-form-item label="打卡状态">
          <el-radio-group v-model="form.signFlag">
            <el-radio
              v-for="dict in signFlagOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="打卡时间" prop="signTime">
          <el-date-picker clearable size="small"
            v-model="form.signTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择打卡时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="用户昵称" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户昵称" />
        </el-form-item>
        <el-form-item label="是否开启提醒">
          <el-radio-group v-model="form.isMessage">
            <el-radio
              v-for="dict in isMessageOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>















  </div>
</template>

<script>
import { listRemind, getRemind, delRemind, addRemind, updateRemind, exportRemind } from "@/api/act/remind";

export default {
  name: "Remind",
  components: {
  },
  data() {
    return {
      xqopen: false,
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 活动打卡记录表格数据
      remindList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 打卡状态字典
      signFlagOptions: [],
      // 是否开启提醒字典
      isMessageOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        actId: null,
        actName: null,
        remindTime: null,
        actLink: null,
        signFlag: null,
        signTime: null,
        userId: null,
        userName: null,
        isMessage: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        actId: [
          { required: true, message: "活动ID不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("common_flag").then(response => {
      this.signFlagOptions = response.data;
    });
    this.getDicts("common_flag").then(response => {
      this.isMessageOptions = response.data;
    });
  },
  methods: {

    /** 查询活动打卡记录列表 */
    getList() {
      this.loading = true;
      listRemind(this.queryParams).then(response => {
        this.remindList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 打卡状态字典翻译
    signFlagFormat(row, column) {
      return this.selectDictLabel(this.signFlagOptions, row.signFlag);
    },
    // 是否开启提醒字典翻译
    isMessageFormat(row, column) {
      return this.selectDictLabel(this.isMessageOptions, row.isMessage);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        actId: null,
        remindId: null,
        actName: null,
        remindTime: null,
        actLink: null,
        signFlag: "0",
        signTime: null,
        userId: null,
        userName: null,
        isMessage: "0",
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.remindId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 详细按钮操作 */
    handleView(row) {
      this.xqopen = true;
      this.form = row;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动打卡记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const remindId = row.remindId || this.ids
      getRemind(remindId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动打卡记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.remindId != null) {
            updateRemind(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRemind(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const remindIds = row.remindId || this.ids;
      this.$confirm('是否确认删除活动打卡记录编号为"' + remindIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRemind(remindIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有活动打卡记录数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportRemind(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    }
  }
};
</script>
