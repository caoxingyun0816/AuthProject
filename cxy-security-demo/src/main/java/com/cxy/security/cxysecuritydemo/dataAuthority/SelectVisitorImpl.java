package com.cxy.security.cxysecuritydemo.dataAuthority;

import org.springframework.stereotype.Component;

import java.util.*;

/***
 * Created by Caoxingyun on 2019/07/17
 */
//@Component
//public class SelectVisitorImpl implements SelectVisitor {

//    @Autowired
//    private FromItemVisitorImpl fromItemVisitor;

//    @Override
//    public void visit(PlainSelect plainSelect) {
//        // 访问 select
////        if (plainSelect.getSelectItems() != null) {
////            for (SelectItem item : plainSelect.getSelectItems()) {
////                item.accept(new SelectItemVisitorImpl());
////            }
////        }
//
//        List<FromItemVisitorImpl> fromItemVisitorList = new ArrayList<>();
//        //创建多实例，不使用@autowired方式注入
//        FromItemVisitorImpl fromItemVisitorWhere = (FromItemVisitorImpl) SpringContextUtils.getBean(FromItemVisitorImpl.class);
//        // 访问from
//       // 实例化 FromItemVisitorImpl --> visit(Table table)
// FromItem fromItem = plainSelect.getFromItem();
//        fromItem.accept(fromItemVisitorWhere);
        //这里会调用 FromItemVisitorImpl 的 visit()
//        fromItemVisitorList.add(fromItemVisitorWhere);
//
//        // 访问where
////        if (plainSelect.getWhere() != null) {
////            plainSelect.getWhere().accept(new ExpressionVisitorImpl());
////        }
//
//        // 访问join
//        if (plainSelect.getJoins() != null) {
//            for (Join join : plainSelect.getJoins()) {
//                FromItemVisitorImpl fromItemVisitorJoin = (FromItemVisitorImpl) SpringContextUtils.getBean(FromItemVisitorImpl.class);
//
//                join.getRightItem().accept(fromItemVisitorJoin);
//
//                fromItemVisitorList.add(fromItemVisitorJoin);
//            }
//        }
//
//        //对BrandSideProject和Brand过滤条件进行筛选，只要一个就够了
//        Map<String, FromItemVisitorImpl> brandFilterWhere = new HashMap<>();
//        Iterator<FromItemVisitorImpl> iterator = fromItemVisitorList.iterator();
//        while (iterator.hasNext()) {
//            FromItemVisitorImpl itemVisitor = iterator.next();
//            if (itemVisitor.getEnhancedCondition() != null) {
//                if (itemVisitor.getTableName().equals(BrandSideProject.class.getAnnotation((javax.persistence.Table.class)).name())
//                        || itemVisitor.getTableName().equals(Brand.class.getAnnotation((javax.persistence.Table.class)).name())) {
//                    brandFilterWhere.put(itemVisitor.getTableName(), itemVisitor);
//                    iterator.remove();
//                }
//            }
//        }
//        if (!brandFilterWhere.isEmpty()) {
//            if (brandFilterWhere.containsKey(BrandSideProject.class.getAnnotation((javax.persistence.Table.class)).name())) {
//                fromItemVisitorList.add(brandFilterWhere
//                        .get(BrandSideProject.class.getAnnotation((javax.persistence.Table.class)).name()));
//            } else {
//                fromItemVisitorList.add(brandFilterWhere
//                        .get(Brand.class.getAnnotation((javax.persistence.Table.class)).name()));
//            }
//        }
//
//        for (FromItemVisitorImpl itemVisitor : fromItemVisitorList) {
//            //过滤增强的条件
//            if (itemVisitor.getEnhancedCondition() != null) {
//                if (plainSelect.getWhere() != null) {
//                    Expression expr = new Parenthesis(plainSelect.getWhere());
//                    Expression enhancedCondition = new Parenthesis(itemVisitor.getEnhancedCondition());
//                    AndExpression and = new AndExpression(enhancedCondition, expr);
//                    plainSelect.setWhere(and);
//                } else {
//                    plainSelect.setWhere(itemVisitor.getEnhancedCondition());
//                }
//            }
//        }
//
//        // 访问 order by
////        if (plainSelect.getOrderByElements() != null) {
////            for (OrderByElement orderByElement : plainSelect
////                    .getOrderByElements()) {
////                orderByElement.getExpression().accept(
////                        new ExpressionVisitorImpl());
////            }
////        }
//
//        // 访问group by having
////        if (plainSelect.getHaving() != null) {
////            plainSelect.getHaving().accept(new ExpressionVisitorImpl());
////        }
//
//
//    }
//
//    @Override
//    public void visit(SetOperationList setOpList) {
////        for (SelectBody plainSelect : setOpList.getSelects()) {
////            plainSelect.accept(new SelectVisitorImpl());
////        }
//    }
//
//    @Override
//    public void visit(WithItem withItem) {
////        withItem.getSelectBody().accept(new SelectVisitorImpl());
//    }
//
//}
