/*
Version: 1.0.0
Author: Sbarraza
App: Manager
date: 31-12-2016
*/
var handleSlimScroll = function() {
        "use strict";
        $("[data-scrollbar=true]").each(function() {
            generateSlimScroll($(this))
        })
    },
    generateSlimScroll = function(e) {
        if (!$(e).attr("data-init")) {
            var a = $(e).attr("data-height");
            a = a ? a : $(e).height();
            var t = {
                height: a,
                alwaysVisible: !0
            };
            /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ? ($(e).css("height", a), $(e).css("overflow-x", "scroll")) : $(e).slimScroll(t), $(e).attr("data-init", !0)
        }
    },
    handlePageContentView = function() {
        "use strict";
        $.when($("#page-loader").addClass("hide")).done(function() {
            $("#page-container").addClass("in")
        })
    },
    panelActionRunning = !1,
    handlePanelAction = function() {
        "use strict";
        return panelActionRunning ? !1 : (panelActionRunning = !0, $(document).on("hover", "[data-click=panel-remove]", function(e) {
            $(this).tooltip({
                title: "Remove",
                placement: "bottom",
                trigger: "hover",
                container: "body"
            }), $(this).tooltip("show")
        }), $(document).on("click", "[data-click=panel-remove]", function(e) {
            e.preventDefault(), $(this).tooltip("destroy"), $(this).closest(".panel").remove()
        }), $(document).on("hover", "[data-click=panel-collapse]", function(e) {
            $(this).tooltip({
                title: "Collapse / Expand",
                placement: "bottom",
                trigger: "hover",
                container: "body"
            }), $(this).tooltip("show")
        }), $(document).on("click", "[data-click=panel-collapse]", function(e) {
            e.preventDefault(), $(this).closest(".panel").find(".panel-body").slideToggle()
        }), $(document).on("hover", "[data-click=panel-reload]", function(e) {
            $(this).tooltip({
                title: "Reload",
                placement: "bottom",
                trigger: "hover",
                container: "body"
            }), $(this).tooltip("show")
        }), $(document).on("click", "[data-click=panel-reload]", function(e) {
            e.preventDefault();
            var a = $(this).closest(".panel");
            if (!$(a).hasClass("panel-loading")) {
                var t = $(a).find(".panel-body"),
                    i = '<div class="panel-loader"><span class="spinner-small"></span></div>';
                $(a).addClass("panel-loading"), $(t).prepend(i), setTimeout(function() {
                    $(a).removeClass("panel-loading"), $(a).find(".panel-loader").remove()
                }, 2e3)
            }
        }), $(document).on("hover", "[data-click=panel-expand]", function(e) {
            $(this).tooltip({
                title: "Expand / Compress",
                placement: "bottom",
                trigger: "hover",
                container: "body"
            }), $(this).tooltip("show")
        }), void $(document).on("click", "[data-click=panel-expand]", function(e) {
            e.preventDefault();
            var a = $(this).closest(".panel"),
                t = $(a).find(".panel-body"),
                i = 40;
            if (0 !== $(t).length) {
                var n = $(a).offset().top,
                    o = $(t).offset().top;
                i = o - n
            }
            if ($("body").hasClass("panel-expand") && $(a).hasClass("panel-expand")) $("body, .panel").removeClass("panel-expand"), $(".panel").removeAttr("style"), $(t).removeAttr("style");
            else if ($("body").addClass("panel-expand"), $(this).closest(".panel").addClass("panel-expand"), 0 !== $(t).length && 40 != i) {
                var s = 40;
                $(a).find(" > *").each(function() {
                    var e = $(this).attr("class");
                    "panel-heading" != e && "panel-body" != e && (s += $(this).height() + 30)
                }), 40 != s && $(t).css("top", s + "px")
            }
            $(window).trigger("resize")
        }))
    },
    handelTooltipPopoverActivation = function() {
        "use strict";
        $("[data-toggle=tooltip]").tooltip(), $("[data-toggle=popover]").popover()
    },
    handleScrollToTopButton = function() {
        "use strict";
        $(document).scroll(function() {
            var e = $(document).scrollTop();
            e >= 200 ? $("[data-click=scroll-top]").addClass("in") : $("[data-click=scroll-top]").removeClass("in")
        }), $("[data-click=scroll-top]").click(function(e) {
            e.preventDefault(), $("html, body").animate({
                scrollTop: $("body").offset().top
            }, 500)
        })
    },
    handleThemePageStructureControl = function() {
        if ($.cookie && $.cookie("theme")) {
            0 !== $(".theme-list").length && ($(".theme-list [data-theme]").closest("li").removeClass("active"), $('.theme-list [data-theme="' + $.cookie("theme") + '"]').closest("li").addClass("active"));
            var e = "/Manager/resources/manager/css/theme/" + $.cookie("theme") + ".css";
            //alert(e);
            $("#theme").attr("href", e)
        }
        $.cookie && $.cookie("sidebar-styling") && 0 !== $(".sidebar").length && "grid" == $.cookie("sidebar-styling") && ($(".sidebar").addClass("sidebar-grid"), $('[name=sidebar-styling] option[value="2"]').prop("selected", !0)), $.cookie && $.cookie("header-styling") && 0 !== $(".header").length && "navbar-inverse" == $.cookie("header-styling") && ($(".header").addClass("navbar-inverse"), $('[name=header-styling] option[value="2"]').prop("selected", !0)), $.cookie && $.cookie("content-gradient") && 0 !== $("#page-container").length && "enabled" == $.cookie("content-gradient") && ($("#page-container").addClass("gradient-enabled"), $('[name=content-gradient] option[value="2"]').prop("selected", !0)), $.cookie && $.cookie("content-styling") && 0 !== $("body").length && "black" == $.cookie("content-styling") && ($("body").addClass("flat-black"), $('[name=content-styling] option[value="2"]').prop("selected", !0)), $(".theme-list [data-theme]").click(function() {
            var e = "/Manager/resources/manager/css/theme/" + $(this).attr("data-theme") + ".css";
            $("#theme").attr("href", e), $(".theme-list [data-theme]").not(this).closest("li").removeClass("active"), $(this).closest("li").addClass("active"), $.cookie("theme", $(this).attr("data-theme"))
        }), $(".theme-panel [name=header-styling]").on("change", function() {
            var e = 1 == $(this).val() ? "navbar-default" : "navbar-inverse",
                a = 1 == $(this).val() ? "navbar-inverse" : "navbar-default";
            $("#header").removeClass(a).addClass(e), $.cookie("header-styling", e)
        }), $(".theme-panel [name=sidebar-styling]").on("change", function() {
            2 == $(this).val() ? ($("#sidebar").addClass("sidebar-grid"), $.cookie("sidebar-styling", "grid")) : ($("#sidebar").removeClass("sidebar-grid"), $.cookie("sidebar-styling", "default"))
        }), $(".theme-panel [name=content-gradient]").on("change", function() {
            2 == $(this).val() ? ($("#page-container").addClass("gradient-enabled"), $.cookie("content-gradient", "enabled")) : ($("#page-container").removeClass("gradient-enabled"), $.cookie("content-gradient", "disabled"))
        }), $(document).on("change", ".theme-panel [name=content-styling]", function() {
            2 == $(this).val() ? ($("body").addClass("flat-black"), $.cookie("content-styling", "black")) : ($("body").removeClass("flat-black"), $.cookie("content-styling", "default"))
        }), $(document).on("change", ".theme-panel [name=sidebar-fixed]", function() {
            1 == $(this).val() ? (2 == $(".theme-panel [name=header-fixed]").val() && (alert("Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar."), $('.theme-panel [name=header-fixed] option[value="1"]').prop("selected", !0), $("#header").addClass("navbar-fixed-top"), $("#page-container").addClass("page-header-fixed")), $("#page-container").addClass("page-sidebar-fixed"), $("#page-container").hasClass("page-sidebar-minified") || generateSlimScroll($('.sidebar [data-scrollbar="true"]'))) : ($("#page-container").removeClass("page-sidebar-fixed"), 0 !== $(".sidebar .slimScrollDiv").length && ($(window).width() <= 979 ? $(".sidebar").each(function() {
                if (!$("#page-container").hasClass("page-with-two-sidebar") || !$(this).hasClass("sidebar-right")) {
                    $(this).find(".slimScrollBar").remove(), $(this).find(".slimScrollRail").remove(), $(this).find('[data-scrollbar="true"]').removeAttr("style");
                    var e = $(this).find('[data-scrollbar="true"]').parent(),
                        a = $(e).html();
                    $(e).replaceWith(a)
                }
            }) : $(window).width() > 979 && ($('.sidebar [data-scrollbar="true"]').slimScroll({
                destroy: !0
            }), $('.sidebar [data-scrollbar="true"]').removeAttr("style"))), 0 === $("#page-container .sidebar-bg").length && $("#page-container").append('<div class="sidebar-bg"></div>'))
        }), $(document).on("change", ".theme-panel [name=header-fixed]", function() {
            1 == $(this).val() ? ($("#header").addClass("navbar-fixed-top"), $("#page-container").addClass("page-header-fixed"), $.cookie("header-fixed", !0)) : (1 == $(".theme-panel [name=sidebar-fixed]").val() && (alert("Default Header with Fixed Sidebar option is not supported. Proceed with Default Header with Default Sidebar."), $('.theme-panel [name=sidebar-fixed] option[value="2"]').prop("selected", !0), $("#page-container").removeClass("page-sidebar-fixed"), 0 === $("#page-container .sidebar-bg").length && $("#page-container").append('<div class="sidebar-bg"></div>')), $("#header").removeClass("navbar-fixed-top"), $("#page-container").removeClass("page-header-fixed"), $.cookie("header-fixed", !1))
        })
    },
    handleThemePanelExpand = function() {
        $(document).on("click", '[data-click="theme-panel-expand"]', function() {
            var e = ".theme-panel",
                a = "active";
            $(e).hasClass(a) ? $(e).removeClass(a) : $(e).addClass(a)
        })
    },
    handleAfterPageLoadAddClass = function() {
        0 !== $("[data-pageload-addclass]").length && $(window).load(function() {
            $("[data-pageload-addclass]").each(function() {
                var e = $(this).attr("data-pageload-addclass");
                $(this).addClass(e)
            })
        })
    },
    handleSavePanelPosition = function(e) {
        "use strict";
        if (0 !== $(".ui-sortable").length) {
            var a = [],
                t = 0;
            $.when($(".ui-sortable").each(function() {
                var e = $(this).find("[data-sortable-id]");
                if (0 !== e.length) {
                    var i = [];
                    $(e).each(function() {
                        var e = $(this).attr("data-sortable-id");
                        i.push({
                            id: e
                        })
                    }), a.push(i)
                } else a.push([]);
                t++
            })).done(function() {
                var t = window.location.href;
                t = t.split("?"), t = t[0], localStorage.setItem(t, JSON.stringify(a)), $(e).find('[data-id="title-spinner"]').delay(500).fadeOut(500, function() {
                    $(this).remove()
                })
            })
        }
    },
    handleIEFullHeightContent = function() {
        var e = window.navigator.userAgent,
            a = e.indexOf("MSIE ");
        (a > 0 || navigator.userAgent.match(/Trident.*rv\:11\./)) && $('.vertical-box-row [data-scrollbar="true"][data-height="100%"]').each(function() {
            var e = $(this).closest(".vertical-box-row"),
                a = $(e).height();
            $(e).find(".vertical-box-cell").height(a)
        })
    },
    handleUnlimitedTabsRender = function() {
        function e(e, a) {
            var t = (parseInt($(e).css("margin-left")), $(e).width()),
                i = $(e).find("li.active").width(),
                n = a > -1 ? a : 150,
                o = 0;
            if ($(e).find("li.active").prevAll().each(function() {
                    i += $(this).width()
                }), $(e).find("li").each(function() {
                    o += $(this).width()
                }), i >= t) {
                var s = i - t;
                o != i && (s += 40), $(e).find(".nav.nav-tabs").animate({
                    marginLeft: "-" + s + "px"
                }, n)
            }
            i != o && o >= t ? $(e).addClass("overflow-right") : $(e).removeClass("overflow-right"), i >= t && o >= t ? $(e).addClass("overflow-left") : $(e).removeClass("overflow-left")
        }

        function a(e, a) {
            var t = $(e).closest(".tab-overflow"),
                i = parseInt($(t).find(".nav.nav-tabs").css("margin-left")),
                n = $(t).width(),
                o = 0,
                s = 0;
            switch ($(t).find("li").each(function() {
                $(this).hasClass("next-button") || $(this).hasClass("prev-button") || (o += $(this).width())
            }), a) {
                case "next":
                    var l = o + i - n;
                    n >= l ? (s = l - i, setTimeout(function() {
                        $(t).removeClass("overflow-right")
                    }, 150)) : s = n - i - 80, 0 != s && $(t).find(".nav.nav-tabs").animate({
                        marginLeft: "-" + s + "px"
                    }, 150, function() {
                        $(t).addClass("overflow-left")
                    });
                    break;
                case "prev":
                    var l = -i;
                    n >= l ? ($(t).removeClass("overflow-left"), s = 0) : s = l - n + 80, $(t).find(".nav.nav-tabs").animate({
                        marginLeft: "-" + s + "px"
                    }, 150, function() {
                        $(t).addClass("overflow-right")
                    })
            }
        }

        function t() {
            $(".tab-overflow").each(function() {
                var a = $(this).width(),
                    t = 0,
                    i = $(this),
                    n = a;
                $(i).find("li").each(function() {
                    var e = $(this);
                    t += $(e).width(), $(e).hasClass("active") && t > a && (n -= t)
                }), e(this, 0)
            })
        }
        $('[data-click="next-tab"]').click(function(e) {
            e.preventDefault(), a(this, "next")
        }), $('[data-click="prev-tab"]').click(function(e) {
            e.preventDefault(), a(this, "prev")
        }), $(window).resize(function() {
            $(".tab-overflow .nav.nav-tabs").removeAttr("style"), t()
        }), t()
    },
    App = function() {
        "use strict";
        return {
            init: function() {
                  this.initPageLoad()
                , this.initThemePanel()
            },
            initPageLoad: function() {
                handlePageContentView()
            },
            initThemePanel: function() {
                handleThemePageStructureControl(), handleThemePanelExpand(), handleResetLocalStorage()
            },
            scrollTop: function() {
                $("html, body").animate({
                    scrollTop: $("body").offset().top
                }, 0)
            }
        }
    }();