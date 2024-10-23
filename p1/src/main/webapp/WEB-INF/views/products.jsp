<section class="section" id="products">
    <div class="container">
        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-lg-4">
                    <div class="item">
                        <div class="thumb">
                            <div class="hover-content">
                                <ul> <%-- ${product.productId} --%>
                                    <li><a href="single-product.jsp?id=1"><i class="fa fa-eye"></i></a></li>
                                    <li><a href="#"><i class="fa fa-star"></i></a></li>
                                    <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                </ul>
                            </div>
                            <img src="${product.image}" alt="${product.name}">
                        </div>
                        <div class="down-content">
                            <h4>${product.name}</h4>
                            <span>$${product.price}</span>
                            <ul class="stars">
                                <c:forEach var="star" begin="1" end="${product.rating}">
                                    <li><i class="fa fa-star"></i></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
