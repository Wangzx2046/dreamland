<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="actName">
        <el-input
          v-model="queryParams.actName"
          placeholder="请输入活动名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动开始时间" prop="actStartTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.actStartTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择活动开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动结束时间" prop="actEndTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.actEndTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择活动结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="提醒时间" prop="remindTime">
        <el-date-picker clearable size="small"
                        v-model="queryParams.remindTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择提醒时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="周期内可参与次数：0代表不限次数" prop="cycleTimes">
        <el-input
          v-model="queryParams.cycleTimes"
          placeholder="请输入周期内可参与次数：0代表不限次数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
      <el-form-item label="活动渠道" prop="actChannel">
        <el-select v-model="queryParams.actChannel" placeholder="请选择活动渠道" clearable size="small">
          <el-option
            v-for="dict in actChannelOptions"
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
          v-hasPermi="['act:info:add']"
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
          v-hasPermi="['act:info:edit']"
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
          v-hasPermi="['act:info:remove']"
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
          v-hasPermi="['act:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="actId" />
      <el-table-column label="活动名称" align="center" prop="actName" />
      <el-table-column label="活动简介" align="center" prop="actIntroduction" />
      <el-table-column label="活动频率" align="center" prop="actFrequency" :formatter="actFrequencyFormat" />
      <el-table-column label="活动开始时间" align="center" prop="actStartTime" width="220">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动结束时间" align="center" prop="actEndTime" width="220">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="提醒时间" align="center" prop="remindTime" width="220">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.remindTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="周期内可参与次数：0代表不限次数" align="center" prop="cycleTimes" />
      <el-table-column label="活动链接" align="center" prop="actLink" />
      <el-table-column label="活动渠道" align="center" prop="actChannel" :formatter="actChannelFormat" />
      <el-table-column label="备注" align="center" prop="remark" />
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
            v-hasPermi="['act:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['act:info:remove']"
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
            <el-form-item label="活动名称：">{{form.actName }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动简介：">{{form.actIntroduction }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动频率：">{{form.actFrequency }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动开始时间：">{{form.actStartTime }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动结束时间：">{{form.actEndTime }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="提醒时间：">{{form.remindTime }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="周期内可参与次数：0代表不限次数：">{{form.cycleTimes }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动链接：">{{form.actLink }}</el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动渠道：">{{form.actChannel }}</el-form-item>
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




    <!-- 添加或修改活动信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="actName">
          <el-input v-model="form.actName" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="活动简介">
          <editor v-model="form.actIntroduction" :min-height="192"/>
        </el-form-item>
        <el-form-item label="活动频率">
          <el-checkbox-group v-model="form.actFrequency">
            <el-checkbox
              v-for="dict in actFrequencyOptions"
              :key="dict.dictValue"
              :label="dict.dictValue">
              {{dict.dictLabel}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="活动开始时间" prop="actStartTime">
          <el-date-picker clearable size="small"
                          v-model="form.actStartTime"
                          type="datetime"
                          value-format="yyyy-MM-dd hh:mm:ss"
                          placeholder="选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动结束时间" prop="actEndTime">
          <el-date-picker clearable size="small"
                          v-model="form.actEndTime"
                          type="datetime"
                          value-format="yyyy-MM-dd hh:mm:ss"
                          placeholder="选择活动结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="提醒时间" prop="remindTime">
          <el-date-picker clearable size="small"
                          v-model="form.remindTime"
                          type="datetime"
                          value-format="yyyy-MM-dd hh:mm:ss"
                          placeholder="选择提醒时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="周期内可参与次数：0代表不限次数" prop="cycleTimes">
          <el-input v-model="form.cycleTimes" placeholder="请输入周期内可参与次数：0代表不限次数" />
        </el-form-item>
        <el-form-item label="活动链接" prop="actLink">
          <el-input v-model="form.actLink" placeholder="请输入活动链接" />
        </el-form-item>
        <el-form-item label="活动渠道" prop="actChannel">
          <el-select v-model="form.actChannel" placeholder="请选择活动渠道">
            <el-option
              v-for="dict in actChannelOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo, exportInfo } from "@/api/act/info";
import Editor from '@/components/Editor';

export default {
  name: "Info",
  components: {
    Editor,
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
      // 活动信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 活动频率字典
      actFrequencyOptions: [],
      // 活动渠道字典
      actChannelOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        actName: null,
        actIntroduction: null,
        actFrequency: null,
        actStartTime: null,
        actEndTime: null,
        remindTime: null,
        cycleTimes: null,
        actLink: null,
        actChannel: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        actName: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        actIntroduction: [
          { required: true, message: "活动简介不能为空", trigger: "blur" }
        ],
        actFrequency: [
          { required: true, message: "活动频率不能为空", trigger: "blur" }
        ],
        actStartTime: [
          { required: true, message: "活动开始时间不能为空", trigger: "blur" }
        ],
        actEndTime: [
          { required: true, message: "活动结束时间不能为空", trigger: "blur" }
        ],
        remindTime: [
          { required: true, message: "提醒时间不能为空", trigger: "blur" }
        ],
        cycleTimes: [
          { required: true, message: "周期内可参与次数：0代表不限次数不能为空", trigger: "blur" }
        ],
        actChannel: [
          { required: true, message: "活动渠道不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("actFrequency").then(response => {
      this.actFrequencyOptions = response.data;
    });
    this.getDicts("actChannel").then(response => {
      this.actChannelOptions = response.data;
    });
  },
  methods: {

    /** 查询活动信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 活动频率字典翻译
    actFrequencyFormat(row, column) {
      return this.selectDictLabels(this.actFrequencyOptions, row.actFrequency);
    },
    // 活动渠道字典翻译
    actChannelFormat(row, column) {
      return this.selectDictLabel(this.actChannelOptions, row.actChannel);
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
        actName: null,
        actIntroduction: null,
        actFrequency: [],
        actStartTime: null,
        actEndTime: null,
        remindTime: null,
        cycleTimes: null,
        actLink: null,
        actChannel: null,
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
      this.ids = selection.map(item => item.actId)
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
      this.title = "添加活动信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const actId = row.actId || this.ids
      getInfo(actId).then(response => {
        this.form = response.data;
        this.form.actFrequency = this.form.actFrequency.split(",");
        this.open = true;
        this.title = "修改活动信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.actFrequency = this.form.actFrequency.join(",");
          if (this.form.actId != null) {
            updateInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      const actIds = row.actId || this.ids;
      this.$confirm('是否确认删除活动信息编号为"' + actIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return delInfo(actIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有活动信息数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportInfo(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      })
    }
  }
};
</script>
