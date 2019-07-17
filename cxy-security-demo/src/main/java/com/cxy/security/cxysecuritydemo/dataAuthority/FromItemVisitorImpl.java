//package com.cxy.security.cxysecuritydemo.dataAuthority;
//
//import com.ecmoho.common.utils.GBeanUtils;
//import com.ecmoho.facade.product.model.table.Brand;
//import com.ecmoho.facade.product.model.table.BrandSideProject;
//import com.ecmoho.service.product.service.RedisService;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.LongValue;
//import net.sf.jsqlparser.expression.StringValue;
//import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
//import net.sf.jsqlparser.expression.operators.relational.*;
//import net.sf.jsqlparser.schema.Column;
//import net.sf.jsqlparser.schema.Table;
//import net.sf.jsqlparser.statement.select.*;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.Scope;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author Sonic Wang
// * @create 2019-04-18 10:20
// **/
//@Component
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
//public class FromItemVisitorImpl implements FromItemVisitor {
//
//    protected final Logger logger = LogManager.getLogger(this.getClass());
//
//    private String tableName;
//    // 声明增强条件
//    private Expression enhancedCondition;
//    @Autowired
//    private RedisService redisService;
//
//    // FROM 表名 <----主要的就是这个，判断用户对这个表有没有权限
//    @Override
//    public void visit(Table table) {
//        tableName = table.getFullyQualifiedName();
//        //判断该表是否是需要操作的表
//        if (isActionTable(tableName)) {
//            List<TableCondition> tableConditions = null;
//
//            //根据表名获取该用户对于该表的限制条件
//            if (tableName.equals(BrandSideProject.class.getAnnotation((javax.persistence.Table.class)).name())
//                    || tableName.equals(Brand.class.getAnnotation((javax.persistence.Table.class)).name())) {
//                try {
//                    String userId = null;
//                    try {
//                        userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//                    } catch (Exception e) {
//                        this.logger.error("加载用户数据权限时，获取不到当前用户id！");
//                    }
//                    //暂时对“应用方用户”不做数据权限控制
//                    if (StringUtils.isNotBlank(userId) && !"APP".equals(userId)) {
//                        List<Map> list = redisService.getHashVal("DataAuthority:" + userId, tableName, List.class);
//                        if (list != null) {
//                            tableConditions = new ArrayList<>();
//                            for (Map map : list) {
//                                TableCondition tableCondition = new TableCondition();
//                                GBeanUtils.populate(tableCondition, map);
//                                tableConditions.add(tableCondition);
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    this.logger.error("获取用户品牌数据权限失败！", e);
//                }
//            } else {
////                tableConditions = redisService.getList(tableName, TableCondition.class);
//            }
//
//            //If the TableConditionList is exist
//            if (tableConditions != null) {
//                //增强sql
//                for (TableCondition tableCondition : tableConditions) {
//                    // 声明表达式数组
//                    Expression[] expressions = null;
//
//                    if ("in".equalsIgnoreCase(tableCondition.getOperator()) || "not in".equalsIgnoreCase(tableCondition.getOperator())) {
//                        List<String> auths = (List<String>) tableCondition.getFieldValue();
//
//                        Column column = new Column(
//                                new Table(table.getAlias() != null ? table.getAlias().getName() : tableName)
//                                , tableCondition.getFieldName());
//
//                        ExpressionList list = new ExpressionList();
//                        List<Expression> expressionList = new ArrayList<>();
//                        auths.forEach(auth -> expressionList.add(new StringValue(auth)));
//                        list.setExpressions(expressionList);
//
//                        InExpression in = new InExpression();
//                        in.setLeftExpression(column);
//                        in.setRightItemsList(list);
//
//                        enhancedCondition = in;
//                        continue;
//                    }
//                    // 如果操作符是between
//                    if ("between".equalsIgnoreCase(tableCondition.getOperator()) || "not between".equalsIgnoreCase(tableCondition.getOperator())) {
//                        //expressions = new Expression[] { new LongValue(tableCondition.getFieldName()),new LongValue(tableCondition.getOperator()),new LongValue(tableCondition.getFieldValue()) };
//                    } else if ("is null".equalsIgnoreCase(tableCondition.getOperator()) || "is not null".equalsIgnoreCase(tableCondition.getOperator())) {
//                        // 如果操作符是 is null或者是is not null的时候
//                        //expressions = new Expression[] { new LongValue(	tableCondition.getFieldName()) };
//                    } else {
//                        // 其他情况,也就是最常用的情况，比如where   1 = 1
//                        Column column = new Column(
//                                new Table(table.getAlias() != null ? table.getAlias().getName() : tableName)
//                                , tableCondition.getFieldName());
//                        if ("1".equals(tableCondition.getFieldName())) {
//                            expressions = new Expression[]{new LongValue(tableCondition.getFieldName()), new LongValue(tableCondition.getFieldValue().toString())};
//                        } else {
//                            expressions = new Expression[]{column, new StringValue(tableCondition.getFieldValue().toString())};
//                        }
//                    }
//                    // 根据运算符对原始数据进行拼接
//                    Expression operator = this.getOperator(
//                            tableCondition.getOperator(), expressions);
//                    if (this.enhancedCondition != null) {
//                        enhancedCondition = new AndExpression(enhancedCondition, operator);
//                    } else {
//                        enhancedCondition = operator;
//                    }
//                }
//            }
//        }
//    }
//
//    // FROM 子查询
//    @Override
//    public void visit(SubSelect subSelect) {
//        // 如果是子查询的话返回到select接口实现类
//        subSelect.getSelectBody().accept(new SelectVisitorImpl());
//    }
//
//    // FROM subjoin
//    @Override
//    public void visit(SubJoin subjoin) {
//        subjoin.getLeft().accept(new FromItemVisitorImpl());
////        subjoin.getJoin().getRightItem().accept(new FromItemVisitorImpl());
//    }
//
//    // FROM 横向子查询
//    @Override
//    public void visit(LateralSubSelect lateralSubSelect) {
//        lateralSubSelect.getSubSelect().getSelectBody()
//                .accept(new SelectVisitorImpl());
//    }
//
//    // FROM value列表
//    @Override
//    public void visit(ValuesList valuesList) {
//    }
//
//    // FROM tableFunction
//    @Override
//    public void visit(TableFunction tableFunction) {
//    }
//
//    @Override
//    public void visit(ParenthesisFromItem aThis) {
//
//    }
//
//    // 将字符串类型的运算符转换成数据库运算语句
//    private Expression getOperator(String op, Expression[] exp) {
////        if ("in".equals(op)) {
////            InExpression in = new InExpression();
////            in.setLeftExpression(exp[0]);
////            in.setRightItemsList(exp[1]);
////            return in;
////        }
//        if ("=".equals(op)) {
//            EqualsTo eq = new EqualsTo();
//            eq.setLeftExpression(exp[0]);
//            eq.setRightExpression(exp[1]);
//            return eq;
//        } else if (">".equals(op)) {
//            GreaterThan gt;
//            gt = new GreaterThan();
//            gt.setLeftExpression(exp[0]);
//            gt.setRightExpression(exp[1]);
//            return gt;
//        } else if (">=".equals(op)) {
//            GreaterThanEquals geq = new GreaterThanEquals();
//            geq.setLeftExpression(exp[0]);
//            geq.setRightExpression(exp[1]);
//            return geq;
//        } else if ("<".equals(op)) {
//            MinorThan mt = new MinorThan();
//            mt.setLeftExpression(exp[0]);
//            mt.setRightExpression(exp[1]);
//            return mt;
//        } else if ("<=".equals(op)) {
//            MinorThanEquals leq = new MinorThanEquals();
//            leq.setLeftExpression(exp[0]);
//            leq.setRightExpression(exp[1]);
//            return leq;
//        } else if ("<>".equals(op)) {
//            NotEqualsTo neq = new NotEqualsTo();
//            neq.setLeftExpression(exp[0]);
//            neq.setRightExpression(exp[1]);
//            return neq;
//        } else if ("is null".equalsIgnoreCase(op)) {
//            IsNullExpression isNull = new IsNullExpression();
//            isNull.setNot(false);
//            isNull.setLeftExpression(exp[0]);
//            return isNull;
//        } else if ("is not null".equalsIgnoreCase(op)) {
//            IsNullExpression isNull = new IsNullExpression();
//            isNull.setNot(true);
//            isNull.setLeftExpression(exp[0]);
//            return isNull;
//        } else if ("like".equalsIgnoreCase(op)) {
//            LikeExpression like = new LikeExpression();
//            like.setLeftExpression(exp[0]);
//            like.setRightExpression(exp[1]);
//            return like;
//        } else if ("not like".equalsIgnoreCase(op)) {
//            LikeExpression nlike = new LikeExpression();
//            nlike.setNot();
//            nlike.setLeftExpression(exp[0]);
//            nlike.setRightExpression(exp[1]);
//            return nlike;
//        } else if ("between".equalsIgnoreCase(op)) {
//            Between bt = new Between();
//            bt.setNot(false);
//            bt.setLeftExpression(exp[0]);
//            bt.setBetweenExpressionStart(exp[1]);
//            bt.setBetweenExpressionEnd(exp[2]);
//            return bt;
//        } else if ("not between".equalsIgnoreCase(op)) {
//            Between bt = new Between();
//            bt.setNot(true);
//            bt.setLeftExpression(exp[0]);
//            bt.setBetweenExpressionStart(exp[1]);
//            bt.setBetweenExpressionEnd(exp[2]);
//            return bt;
//        } else {
//            // 如果没有该运算符对应的语句
//            return null;
//        }
//
//    }
//
//    public Expression getEnhancedCondition() {
//        return enhancedCondition;
//    }
//
//    public String getTableName() {
//        return tableName;
//    }
//
//    // 判断传入的table是否是要进行操作的table
//    public boolean isActionTable(String tableName) {
//        if (tableName.equals(BrandSideProject.class.getAnnotation((javax.persistence.Table.class)).name())
//                || tableName.equals(Brand.class.getAnnotation((javax.persistence.Table.class)).name())) {
//            return true;
//        }
////        if(redisService.checkKey(tableName)) {
////            return true;
////        }
//        return false;
//    }
//}
//
