<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="header.jsp"/>

<ul class="nav nav-tabs" id="myTab" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" id="product-list-tab" data-bs-toggle="tab" data-bs-target="#product-list"
                type="button" role="tab" aria-controls="product-list" aria-selected="true">Список продуктов</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="category-list-tab" data-bs-toggle="tab" data-bs-target="#category-list"
                type="button" role="tab" aria-controls="category-list" aria-selected="false">Список категорий</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="user-list-tab" data-bs-toggle="tab" data-bs-target="#user-list"
                type="button" role="tab" aria-controls="user-list" aria-selected="false">Список пользователей</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="order-list-tab" data-bs-toggle="tab" data-bs-target="#order-list"
                type="button" role="tab" aria-controls="order-list" aria-selected="false">Список заказов</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" id="comment-list-tab" data-bs-toggle="tab" data-bs-target="#comment-list"
                type="button" role="tab" aria-controls="comment-list" aria-selected="false">Список комментариев</button>
    </li>
</ul>

<div class="tab-content" id="myTabContent">
    <div class="tab-pane fade show active" id="product-list" role="tabpanel" aria-labelledby="product-list-tab">
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
                <button class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#addProductModal"
                onclick="fillData(`${product}`)">Edit</button>
                <a href="/admin/deleteProduct/${product.id}" class="btn btn-outline-danger">Delete</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- open modal for product adding -->
        <button type="button" class="btn btn-outline-success"
                data-bs-toggle="modal" data-bs-target="#addProductModal">Добавить продукт</button>

        <!--add product modal-->
        <div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addProductModalLabel">Добавление продукта</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form action="/admin/addProduct" modelAttribute="newProduct">
                            <div class="mb-3 has-validation" id="category">
                                <form:label path="categoryId" cssClass="form-label">Category ID</form:label>
                                <form:select cssClass="form-select" path="categoryId">
                                    <c:forEach  items="${categoryListForSelect}" var="category">
                                        <form:option value="${category.id}">${category.title}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3 has-validation" id="title">
                                <form:label path="title" cssClass="form-label">Title</form:label>
                                <form:input type="text" cssClass="form-control" path="title" />
                            </div>
                            <div class="mb-3 has-validation" id="price">
                                <form:label path="price" cssClass="form-label">Price</form:label>
                                <form:input type="number" cssClass="form-control" path="price" />
                            </div>
                            <div class="mb-3 has-validation" id="amount">
                                <form:label path="amount" cssClass="form-label">Amount</form:label>
                                <form:input type="number" cssClass="form-control" path="amount" />
                            </div>
                            <div class="mb-3 has-validation" id="desc">
                                <form:label path="description" cssClass="form-label">Description</form:label>
                                <form:input type="text" cssClass="form-control" path="description" />
                            </div>
                            <div class="mb-3" id="image">
                                <form:input path="imgPath" type="file"/>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button  type="submit" class="btn btn-primary">Submit</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="category-list" role="tabpanel" aria-labelledby="category-list-tab-tab">

    </div>
    <div class="tab-pane fade" id="user-list" role="tabpanel" aria-labelledby="user-list-tab">

    </div>
    <div class="tab-pane fade" id="order-list" role="tabpanel" aria-labelledby="order-list-tab">

    </div>
    <div class="tab-pane fade" id="comment-list" role="tabpanel" aria-labelledby="comment-list-tab">

    </div>
</div>

<script>
    function fillData(product) {
        console.log(product);
    }
</script>

<c:import url="footer.jsp"/>