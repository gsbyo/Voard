<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>




        <!DOCTYPE html>
        <html>

        <head>
            <title>Youtube Board</title>
            <link rel="stylesheet" type="text/css" href="/css/board.css" </head>

            <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

        <body>
            <div class="container">
                <div id="top-container">
                    <div id="title-box">
                        <h2>VIDEO BOARD (Youtube)</h2>
                    </div>
                    <div id="user-box">
                        <c:choose>
                            <c:when test="${user != null}">
                                <div id="user-wrap">
                                    <div id="add-box">
                                        <input type="text" id="url-text">
                                        <button id="add-button">+</button>
                                    </div>
                                    <div id="login-box">
                                        <button onClick="location.href='/logout'">로그아웃</button>
                                    </div>
                                </div>

                            </c:when>
                            <c:otherwise>
                                <div>
                                    <p><button onClick="location.href='/login'">로그인</button></p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div id="list-container">
                    <div id="tab-container">
                        <div id="category-box">
                            <h3>CATEGORY</h3>

                            <c:if test="${user != null}">
                            <div padding: 10px;"><input type="text" style="width: 50%;" id="category-text"><button
                                    id="category-add-button">+</button></div>
                            <div id="category-list">
                                <p onclick="location.href='/board'">전체</p>
                                <c:forEach var="index" items="${category}">
                                    <p class="category" data-id=${index.id}>${index.name}</p>
                                </c:forEach>
                          
                            </div>
                        </c:if>

                        </div>
                    </div>
                    <%-- 무한 스크롤 옵저브 --%>
                        <div id="video-container">
                            <div id="video-wrap">
                                <c:forEach var="index" items="${post}">
                                    <div class="video-box">
                                        <div style="padding: 10px; padding-bottom: 0;">
                                            <iframe width="100%" height="300"
                                                src="https://www.youtube.com/embed/${index.url}"
                                                title="YouTube video player" frameborder="0"
                                                allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                                allowfullscreen></iframe>
                                        </div>
                                        <div
                                            style="padding: 10px; padding-top: 0;  display: flex; flex-wrap: wrap; margin-top:-4px;">
                                            <span
                                                style="width: 50%; background-color: #cc0000; color: white; text-align: center; font-size: 14px;">${index.category.name}</span>
                                            <button class="video-del-button"
                                                style="width: 50%; font-size: 14px; border-left: 1px solid gainsboro;"
                                                data-id="${index.id}">삭제하기</button>
                                        </div>
                                    </div>
                                </c:forEach>

                            </div>

                            <c:if test="${user != null}">
                                <div id="end-box" style="border: 1px solid red;"></div>
                            </c:if>






                        </div>
                </div>
            </div>
            <script>
                var v_id = "";
                var page = 1;
                var end = 0;

                //카테고리 목록 클릭 시
                $('.category').on('click', (e) => {
                    $(location).attr('href', "/board/post/category/" + $(e.target).data('id'));
                })


                //목록 삭제 버튼
                $('.video-del-button').on('click', (e) => {
                    $.ajax({
                        type: 'delete',
                        url: '/board/post/del/' + $(e.target).data('id'),
                        success: function () {
                            location.reload();
                        }
                    })
                })


                //카테고리 추가 버튼
                $('#category-add-button').on('click', () => {
                    $.ajax({
                        url: '/board/category/add',
                        type: 'post',
                        data: {
                            name: $('#category-text').val()
                        },
                        success: function () {
                            location.reload();
                        }
                    })


                })

                //목록 추가 버튼
                $('#add-button').on('click', function () {
                    if ($('#video-add').length > 0) {
                        return alert('완료 혹은 취소를 확인해주세요.')
                    }
                    const urlParams = new URL($('#url-text').val()).searchParams;

                    v_id = urlParams.get('v');

                    console.log(v_id);

                    // if (v_id = "") return alert("올바른 url을 입력해주세요");

                    $('#video-wrap').prepend(
                        `<div id="video-add">
                                    <div style="padding: 10px;">
                                        <iframe width="100%" height="300"
                                            src="https://www.youtube.com/embed/\${v_id}" title="YouTube video player"
                                            frameborder="0"
                                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                            allowfullscreen></iframe>
                                        <select id ="category-options">
                                           <option value="null">없음</option>
                                        </select>
                                        <button id="comple-button">완료</button>
                                        <button id="cancel-button">취소</button>
                                    </div>
                          </div>`

                    )

                    for (var i = 0; i < $('.category').length; ++i) {
                        $('#category-options').append(`<option value = \${ $('.category').eq(i).data('id')}>\${ $('.category').eq(i).text() }</option>`);
                    }

                })


                //목록 추가 시에 동적 생성 된 취소 버튼
                $(document).on('click', '#cancel-button', function () {
                    $('#video-add').remove();
                })

                //목록 추가 시에 동적 생성 된 완료 버튼
                $(document).on('click', '#comple-button', function () {
                    if ($('#category-options option:selected').val() == "null") {
                        var category = null;
                    }
                    else {
                        var category = {
                            id: Number($('#category-options option:selected').val())
                        }
                    }

                    $.ajax({
                        type: 'post',
                        url: '/board/post/add',
                        contentType: "application/json; charset=UTF-8",
                        data: JSON.stringify({
                            url: v_id,
                            category: category
                        }),
                        success: function () {
                            location.reload();
                        }

                    })
                })

                //무한 스크롤 
                const options = {
                    root: null,
                    rootMargin: "0px",
                    threshold: 1.0,
                };

                const observer = new IntersectionObserver((entries) => {
                    entries.forEach((entry) => {
                        if (end == 0) {
                            if ($(location).attr('pathname').includes("category")) {
                                var cul_category = $(location).attr('pathname').split('/')[4]
                                $.ajax({
                                    type: 'get',
                                    url: '/board/post/category?id=' + cul_category + "&page=" + page,
                                    success: function (res) {
                                        if (res == null || res == '') return end = 1;

                                        ++page;

                                        for (var i = 0; i < res.length; ++i) {
                                            $('#video-wrap').append(
                                                `<div class="video-box">
                                                    <div style="padding: 10px; padding-bottom: 0;">
                                                        <iframe width="100%" height="300"
                                                            src="https://www.youtube.com/embed/\${res[0].url}"
                                                            title="YouTube video player" frameborder="0"
                                                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                                            allowfullscreen></iframe>
                                                    </div>
                                                    <div
                                                        style="padding: 10px; padding-top: 0;  display: flex; flex-wrap: wrap; margin-top:-4px;">
                                                        <span
                                                            style="width: 50%; background-color: #cc0000; color: white; text-align: center; font-size: 14px;">${index.category.name}</span>
                                                        <button class="video-del-button"
                                                            style="width: 50%; font-size: 14px; border-left: 1px solid gainsboro;"
                                                            data-id="\${res[i].id}">삭제하기</button>
                                                    </div>
                                                </div>`
                                            )
                                        }
                                    }
                                })
                            } else {
                                $.ajax({
                                    type: 'get',
                                    url: '/board/get/post?page=' + page,
                                    success: function (res) {
                                        if (res == null || res == '') return end = 1;

                                        ++page;

                                        for (var i = 0; i < res.length; ++i) {
                                            $('#video-wrap').append(
                                                `<div class="video-box">
                                                    <div style="padding: 10px; padding-bottom: 0;">
                                                        <iframe width="100%" height="300"
                                                            src="https://www.youtube.com/embed/\${res[0].url}"
                                                            title="YouTube video player" frameborder="0"
                                                            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                                            allowfullscreen></iframe>
                                                    </div>
                                                    <div
                                                        style="padding: 10px; padding-top: 0;  display: flex; flex-wrap: wrap; margin-top:-4px;">
                                                        <span
                                                            style="width: 50%; background-color: #cc0000; color: white; text-align: center; font-size: 14px;">${index.category.name}</span>
                                                        <button class="video-del-button"
                                                            style="width: 50%; font-size: 14px; border-left: 1px solid gainsboro;"
                                                            data-id="\${res[i].id}">삭제하기</button>
                                                    </div>
                                                </div>`
                                            )
                                        }
                                    }
                                })

                            }


                        }
                        //

                    });
                }, options);

                if ($('#end-box').length != 0) {
                    const target = document.querySelector('#end-box');

                    observer.observe(target);
                }

            </script>
        </body>

        </html>