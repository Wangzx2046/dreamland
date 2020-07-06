package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Dao}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

 @Resource
 private ${table.mapperName} ${table.entityPropertyName}Dao;

 @Override
 public List< ${entity}> list( ${entity} ${table.entityPropertyName}) {
  QueryWrapper< ${entity}> queryWrapper = new QueryWrapper<>(${table.entityPropertyName});
   if (StringUtils.isNotBlank(${table.entityPropertyName}.getBlurry())) {
    queryWrapper.eq("name", ${table.entityPropertyName}.getBlurry())
    .or().eq("description", ${table.entityPropertyName}.getBlurry());
   }
   return  ${table.entityPropertyName}Dao.selectList(queryWrapper);
   }


  }
</#if>
