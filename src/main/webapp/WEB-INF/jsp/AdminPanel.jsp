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

<div class="tab-content" id="myTabContent" style="width: 90%">
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
                <td>${product.category.title}</td>
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
                <a href="/admin/viewProductEdit/${product.id}" class="btn btn-outline-primary">✎</a>
                <a href="/admin/deleteProduct/${product.id}" class="btn btn-outline-danger">🗑</a>
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
                            <div class="mb-3">
                                <form:label path="categoryId" cssClass="form-label">Категория</form:label>
                                <form:select cssClass="form-select" path="categoryId">
                                    <c:forEach  items="${categoryList}" var="category">
                                        <form:option value="${category.id}">${category.title}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="title" cssClass="form-label">Название</form:label>
                                <form:input type="text" cssClass="form-control" path="title"
                                    required="true" maxlength="50"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="price" cssClass="form-label">Цена</form:label>
                                <form:input type="number" cssClass="form-control" path="price"
                                    required="true" />
                            </div>
                            <div class="mb-3">
                                <form:label path="amount" cssClass="form-label">Количество</form:label>
                                <form:input type="number" cssClass="form-control" path="amount"
                                    required="true" />
                            </div>
                            <div class="mb-3">
                                <form:label path="description" cssClass="form-label">Описание</form:label>
                                <form:input type="text" cssClass="form-control" path="description"
                                    required="true" minlength="10" maxlength="255"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="imgPath" cssClass="form-label">Ссылка на изображение</form:label>
                                <form:input path="imgPath" cssClass="form-control" type="text"
                                    required="true" maxlength="255"/>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button  type="submit" class="btn btn-primary">Сохранить</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="category-list" role="tabpanel" aria-labelledby="category-list-tab-tab">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th>#</th>
                <th>Главная категория</th>
                <th>Категория</th>
                <th>Опции</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categoryList}" var="category">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.parentCategory.title}</td>
                    <td>${category.title}</td>
                    <td>
                        <a href="/admin/viewCategoryEdit/${category.id}" class="btn btn-outline-primary">✎</a>
                        <a href="/admin/deleteCategory/${category.id}" class="btn btn-outline-danger">🗑</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- open modal for category adding -->
        <button type="button" class="btn btn-outline-success"
                data-bs-toggle="modal" data-bs-target="#addCategoryModal">Добавить категорию</button>

        <!--add category modal-->
        <div class="modal fade" id="addCategoryModal" tabindex="-1" aria-labelledby="addCategoryModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCategoryModalLabel">Добавление категории</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form action="/admin/addCategory" modelAttribute="newCategory">
                            <div class="mb-3">
                                <form:label path="parentCategoryId" cssClass="form-label">Главная категория</form:label>
                                <form:select cssClass="form-select" path="parentCategoryId">
                                    <form:option value="0">Главная категория</form:option>
                                    <c:forEach  items="${categoryList}" var="category">
                                        <form:option value="${category.id}">${category.title}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="title" cssClass="form-label">Название</form:label>
                                <form:input type="text" cssClass="form-control" path="title"
                                    required="true" maxlength="50"/>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button  type="submit" class="btn btn-primary">Сохранить</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="user-list" role="tabpanel" aria-labelledby="user-list-tab">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th>#</th>
                <th>Логин</th>
                <th>Почта</th>
                <th>Администратор</th>
                <th>Опции</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.permissions}</td>
                    <td>
                        <a href="/admin/viewUserEdit/${user.id}" class="btn btn-outline-primary">✎</a>
                        <a href="/admin/deleteUser/${user.id}" class="btn btn-outline-danger">🗑</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- open modal for user adding -->
        <button type="button" class="btn btn-outline-success"
                data-bs-toggle="modal" data-bs-target="#addUserModal">Добавить пользователя</button>

        <!--add user modal-->
        <div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addUserModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addUserModalLabel">Добавление пользователя</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form action="/admin/addUser" modelAttribute="newUser">
                            <div class="mb-3">
                                <form:label path="username" cssClass="form-label">Логин</form:label>
                                <form:input type="text" path="username" cssClass="form-control"
                                    required="true" minlength="5" maxlength="20"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="email" cssClass="form-label">Почта</form:label>
                                <form:input type="email" cssClass="form-control" path="email"
                                    required="true" maxlength="50"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="password" cssClass="form-label">Пароль</form:label>
                                <form:input type="password" cssClass="form-control" path="password"
                                    required="true" minlength="6" maxlength="255"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="permissions" cssClass="form-label">Роли</form:label>
                                <form:select cssClass="form-select" path="permissions">
                                    <form:option value="ROLE_ADMIN">Администратор</form:option>
                                    <form:option value="ROLE_USER">Пользователь</form:option>
                                </form:select>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button  type="submit" class="btn btn-primary">Сохранить</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="order-list" role="tabpanel" aria-labelledby="order-list-tab">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th>#</th>
                <th>Пользователь</th>
                <th>Продукт</th>
                <th>Опции</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderList}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.user.username}</td>
                    <td>${order.product.title}</td>
                    <td>
                        <a href="/admin/viewOrderEdit/${order.id}" class="btn btn-outline-primary">✎</a>
                        <a href="/admin/deleteOrder/${order.id}" class="btn btn-outline-danger">🗑</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- open modal for order adding -->
        <button type="button" class="btn btn-outline-success"
                data-bs-toggle="modal" data-bs-target="#addOrderModal">Добавить заказ</button>

        <!--add order modal-->
        <div class="modal fade" id="addOrderModal" tabindex="-1" aria-labelledby="addOrderModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addOrderModalLabel">Добавление заказа</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form action="/admin/addOrder" modelAttribute="newOrder">
                            <div class="mb-3">
                                <form:label path="userId" cssClass="form-label">Пользователь</form:label>
                                <form:select path="userId" cssClass="form-select">
                                    <c:forEach  items="${userList}" var="user">
                                        <form:option value="${user.id}">${user.username} (${user.email})</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="productId" cssClass="form-label">Продукт</form:label>
                                <form:select path="productId" cssClass="form-select">
                                    <c:forEach  items="${productList}" var="product">
                                        <form:option value="${product.id}">${product.title} (${product.category.title})</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button  type="submit" class="btn btn-primary">Сохранить</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="comment-list" role="tabpanel" aria-labelledby="comment-list-tab">
        <table class="table table-responsive">
            <thead>
            <tr>
                <th>#</th>
                <th>Пользователь</th>
                <th>Заголовок</th>
                <th>Комментарий</th>
                <th>Рейтинг</th>
                <th>Продукт</th>
                <th>Опции</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${commentList}" var="comment">
                <tr>
                    <td>${comment.id}</td>
                    <td>${comment.user.username}</td>
                    <td>${comment.title == null ? "Нет заголовка" : comment.title}</td>
                    <td>${comment.comment}</td>
                    <td>${comment.rating}</td>
                    <td>${comment.product.title}</td>
                    <td>
                        <a href="/admin/viewCommentEdit/${comment.id}" class="btn btn-outline-primary">✎</a>
                        <a href="/admin/deleteComment/${comment.id}" class="btn btn-outline-danger">🗑</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- open modal for comment adding -->
        <button type="button" class="btn btn-outline-success"
                data-bs-toggle="modal" data-bs-target="#addCommentModal">Добавить комментарий</button>

        <!--add comment modal-->
        <div class="modal fade" id="addCommentModal" tabindex="-1" aria-labelledby="addCommentModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addCommentModalLabel">Добавление комментария</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form action="/admin/addComment" modelAttribute="newComment">
                            <div class="mb-3">
                                <form:label path="userId" cssClass="form-label">Пользователь</form:label>
                                <form:select path="userId" cssClass="form-select">
                                    <c:forEach  items="${userList}" var="user">
                                        <form:option value="${user.id}">${user.username} (${user.email})</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="mb-3">
                                <form:label path="title" cssClass="form-label">Заголовок</form:label>
                                <form:input type="text" path="title" cssClass="form-control"
                                    maxlength="50" />
                            </div>
                            <div class="mb-3">
                                <form:label path="comment" cssClass="form-label">Комментарий</form:label>
                                <form:input type="text" path="comment" cssClass="form-control"
                                    required="true" maxlength="255"/>
                            </div>
                            <div class="mb-3">
                                <form:label path="rating" cssClass="form-label">Рейтинг</form:label>
                                <form:input path="rating" type="number" cssClass="form-control"
                                    min="0" max="5" />
                            </div>
                            <div class="mb-3">
                                <form:label path="productId" cssClass="form-label">Продукт</form:label>
                                <form:select path="productId" cssClass="form-select">
                                    <c:forEach  items="${productList}" var="product">
                                        <form:option value="${product.id}">${product.title} (${product.category.title})</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Закрыть</button>
                            <button  type="submit" class="btn btn-primary">Сохранить</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:import url="footer.jsp"/>