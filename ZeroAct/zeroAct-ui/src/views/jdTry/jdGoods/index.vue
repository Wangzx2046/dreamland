<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型3种草官 5付费" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型3种草官 5付费" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="类型3种草官 5付费" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入类型3种草官 5付费"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型3种草官 5付费" prop="isconfig">
        <el-input
          v-model="queryParams.isconfig"
          placeholder="请输入类型3种草官 5付费"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型3种草官 5付费" prop="filtertype">
        <el-select v-model="queryParams.filtertype" placeholder="请选择类型3种草官 5付费" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="商品编码" prop="trialactivityid">
        <el-input
          v-model="queryParams.trialactivityid"
          placeholder="请输入商品编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编号" prop="sku">
        <el-input
          v-model="queryParams.sku"
          placeholder="请输入编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片" prop="skuimg">
        <el-input
          v-model="queryParams.skuimg"
          placeholder="请输入图片"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="skutitle">
        <el-input
          v-model="queryParams.skutitle"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试用价格" prop="trialprice">
        <el-input
          v-model="queryParams.trialprice"
          placeholder="请输入试用价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原价" prop="jdprice">
        <el-input
          v-model="queryParams.jdprice"
          placeholder="请输入原价"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签" prop="taglist">
        <el-input
          v-model="queryParams.taglist"
          placeholder="请输入标签"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供数目" prop="supplynum">
        <el-input
          v-model="queryParams.supplynum"
          placeholder="请输入提供数目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人数" prop="applynum">
        <el-input
          v-model="queryParams.applynum"
          placeholder="请输入申请人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人数" prop="brokerinfo">
        <el-input
          v-model="queryParams.brokerinfo"
          placeholder="请输入申请人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人数" prop="applystate">
        <el-input
          v-model="queryParams.applystate"
          placeholder="请输入申请人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人数" prop="activitytype">
        <el-select v-model="queryParams.activitytype" placeholder="请选择申请人数" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请人数" prop="trialprocess">
        <el-input
          v-model="queryParams.trialprocess"
          placeholder="请输入申请人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人数" prop="activitystatus">
        <el-select v-model="queryParams.activitystatus" placeholder="请选择申请人数" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="申请人数" prop="showpoint">
        <el-input
          v-model="queryParams.showpoint"
          placeholder="请输入申请人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人数" prop="isnewactivity">
        <el-input
          v-model="queryParams.isnewactivity"
          placeholder="请输入申请人数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['jdTry:jdGoods:add']"
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
          v-hasPermi="['jdTry:jdGoods:edit']"
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
          v-hasPermi="['jdTry:jdGoods:remove']"
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
          v-hasPermi="['jdTry:jdGoods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="jdGoodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="类型3种草官 5付费" align="center" prop="type" />
      <el-table-column label="类型3种草官 5付费" align="center" prop="sort" />
      <el-table-column label="类型3种草官 5付费" align="center" prop="isconfig" />
      <el-table-column label="类型3种草官 5付费" align="center" prop="filtertype" />
      <el-table-column label="商品编码" align="center" prop="trialactivityid" />
      <el-table-column label="编号" align="center" prop="sku" />
      <el-table-column label="图片" align="center" prop="skuimg" />
      <el-table-column label="标题" align="center" prop="skutitle" />
      <el-table-column label="试用价格" align="center" prop="trialprice" />
      <el-table-column label="原价" align="center" prop="jdprice" />
      <el-table-column label="标签" align="center" prop="taglist" />
      <el-table-column label="提供数目" align="center" prop="supplynum" />
      <el-table-column label="申请人数" align="center" prop="applynum" />
      <el-table-column label="申请人数" align="center" prop="brokerinfo" />
      <el-table-column label="申请人数" align="center" prop="applystate" />
      <el-table-column label="申请人数" align="center" prop="activitytype" />
      <el-table-column label="申请人数" align="center" prop="trialprocess" />
      <el-table-column label="申请人数" align="center" prop="activitystatus" />
      <el-table-column label="申请人数" align="center" prop="showpoint" />
      <el-table-column label="申请人数" align="center" prop="isnewactivity" />
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
            v-hasPermi="['jdTry:jdGoods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['jdTry:jdGoods:remove']"
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
    <el-form-item label="id：">{{form.id }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="类型3种草官 5付费：">{{form.type }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.sort }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.isconfig }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.filtertype }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="商品编码：">{{form.trialactivityid }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="编号：">{{form.sku }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="图片：">{{form.skuimg }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="标题：">{{form.skutitle }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="试用价格：">{{form.trialprice }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="原价：">{{form.jdprice }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="标签：">{{form.taglist }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="提供数目：">{{form.supplynum }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="申请人数：">{{form.applynum }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.brokerinfo }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.applystate }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.activitytype }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.trialprocess }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.activitystatus }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.showpoint }}</el-form-item>
  </el-col>
   <el-col :span="24">
    <el-form-item label="${column.columnComment}：">{{form.isnewactivity }}</el-form-item>
  </el-col>
 </el-row>
</el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="xqopen = false">关 闭</el-button>
  </div>
</el-dialog>




    <!-- 添加或修改试用商品对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="类型3种草官 5付费" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型3种草官 5付费">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="类型3种草官 5付费" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入类型3种草官 5付费" />
        </el-form-item>
        <el-form-item label="类型3种草官 5付费" prop="isconfig">
          <el-input v-model="form.isconfig" placeholder="请输入类型3种草官 5付费" />
        </el-form-item>
        <el-form-item label="类型3种草官 5付费" prop="filtertype">
          <el-select v-model="form.filtertype" placeholder="请选择类型3种草官 5付费">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="商品编码" prop="trialactivityid">
          <el-input v-model="form.trialactivityid" placeholder="请输入商品编码" />
        </el-form-item>
        <el-form-item label="编号" prop="sku">
          <el-input v-model="form.sku" placeholder="请输入编号" />
        </el-form-item>
        <el-form-item label="图片" prop="skuimg">
          <el-input v-model="form.skuimg" placeholder="请输入图片" />
        </el-form-item>
        <el-form-item label="标题" prop="skutitle">
          <el-input v-model="form.skutitle" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="试用价格" prop="trialprice">
          <el-input v-model="form.trialprice" placeholder="请输入试用价格" />
        </el-form-item>
        <el-form-item label="原价" prop="jdprice">
          <el-input v-model="form.jdprice" placeholder="请输入原价" />
        </el-form-item>
        <el-form-item label="标签" prop="taglist">
          <el-input v-model="form.taglist" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="提供数目" prop="supplynum">
          <el-input v-model="form.supplynum" placeholder="请输入提供数目" />
        </el-form-item>
        <el-form-item label="申请人数" prop="applynum">
          <el-input v-model="form.applynum" placeholder="请输入申请人数" />
        </el-form-item>
        <el-form-item label="申请人数" prop="brokerinfo">
          <el-input v-model="form.brokerinfo" placeholder="请输入申请人数" />
        </el-form-item>
        <el-form-item label="申请人数" prop="applystate">
          <el-input v-model="form.applystate" placeholder="请输入申请人数" />
        </el-form-item>
        <el-form-item label="申请人数" prop="activitytype">
          <el-select v-model="form.activitytype" placeholder="请选择申请人数">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="申请人数" prop="trialprocess">
          <el-input v-model="form.trialprocess" placeholder="请输入申请人数" />
        </el-form-item>
        <el-form-item label="申请人数">
          <el-radio-group v-model="form.activitystatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="申请人数" prop="showpoint">
          <el-input v-model="form.showpoint" placeholder="请输入申请人数" />
        </el-form-item>
        <el-form-item label="申请人数" prop="isnewactivity">
          <el-input v-model="form.isnewactivity" placeholder="请输入申请人数" />
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
import { listJdGoods, getJdGoods, delJdGoods, addJdGoods, updateJdGoods, exportJdGoods } from "@/api/jdTry/jdGoods";

export default {
  name: "JdGoods",
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
      // 试用商品表格数据
      jdGoodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        sort: null,
        isconfig: null,
        filtertype: null,
        trialactivityid: null,
        sku: null,
        skuimg: null,
        skutitle: null,
        trialprice: null,
        jdprice: null,
        taglist: null,
        supplynum: null,
        applynum: null,
        brokerinfo: null,
        applystate: null,
        activitytype: null,
        trialprocess: null,
        activitystatus: null,
        showpoint: null,
        isnewactivity: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    /** 查询试用商品列表 */
    getList() {
      this.loading = true;
      listJdGoods(this.queryParams).then(response => {
        this.jdGoodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        sort: null,
        isconfig: null,
        filtertype: null,
        trialactivityid: null,
        sku: null,
        skuimg: null,
        skutitle: null,
        trialprice: null,
        jdprice: null,
        taglist: null,
        supplynum: null,
        applynum: null,
        brokerinfo: null,
        applystate: null,
        activitytype: null,
        trialprocess: null,
        activitystatus: 0,
        showpoint: null,
        isnewactivity: null
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
      this.ids = selection.map(item => item.id)
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
      this.title = "添加试用商品";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getJdGoods(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改试用商品";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateJdGoods(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addJdGoods(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除试用商品编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delJdGoods(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有试用商品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportJdGoods(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        })
    }
  }
};
</script>
