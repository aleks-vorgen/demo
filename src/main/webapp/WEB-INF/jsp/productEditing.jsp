<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="header.jsp"/>

<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" id="list-tab" data-bs-toggle="tab" data-bs-target="#list"
                type="button" role="tab" aria-controls="list" aria-selected="true">Список продуктов</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="add-tab" data-bs-toggle="tab" data-bs-target="#add"
                type="button" role="tab" aria-controls="add" aria-selected="false">Добавить</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="edit-tab" data-bs-toggle="tab" data-bs-target="#edit"
                type="button" role="tab" aria-controls="edit" aria-selected="false">Редактировать</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="delete-tab" data-bs-toggle="tab" data-bs-target="#delete"
                type="button" role="tab" aria-controls="delete" aria-selected="false">Удалить</button>
    </li>
</ul>

<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="list" role="tabpanel" aria-labelledby="list-tab">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th>#</th>
                <th>Категория</th>
                <th>Название</th>
                <th>Цена</th>
                <th>Количество</th>
                <th>Описание</th>
                <th>Изображение</th>
                <th>Опции</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.categoryId}</td>
                <td style="max-width: 200px; overflow: auto">
                    ${product.title}</td>
                <td>${product.price}</td>
                <td>${product.amount}</td>
                <td style="max-width: 200px; overflow: auto">
                    ${product.description}
                </td>
                <td style="max-width: 300px; overflow: auto">
                    ${product.imgPath}
                </td>
                <td>
                <a href="/products/admin/edit/${product.id}" class="btn btn-outline-primary">Edit</a>
                <a href="/products/admin/delete/${product.id}" class="btn btn-outline-danger">Delete</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="tab-pane fade" id="add" role="tabpanel" aria-labelledby="add-tab">

    </div>
    <div class="tab-pane fade" id="edit" role="tabpanel" aria-labelledby="edit-tab">

    </div>
    <div class="tab-pane fade" id="delete" role="tabpanel" aria-labelledby="delete-tab">

    </div>
</div>

<script>

</script>

<c:import url="footer.jsp"/>