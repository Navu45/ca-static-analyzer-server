<#import "layout.ftl" as page>

<@page.layout "Profile | Clean Architecture Analyzer">

    <form action="/analyze">
        <div class="mb-3 mt-3">
            <h3>To start code analyzing, enter the form below:</h3>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="owner" type="text" placeholder="Owner nickname"
                       autofocus="autofocus" required/>
            </label>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="repo" type="text" placeholder="Repository name" required/>
            </label>
        </div>

        <div class="form-group">
            <label>
                <input class="form-control"  name="sourceDir" type="text"
                       placeholder="Source files' directory (preferably .../main)"/>
            </label>
        </div>

        <button class="btn btn-primary mb-3" type="submit">Analyze</button>

        <div class="mb-3 mt-3">
            <#if review??>
                <h3>Last review - ${review.getDate()}</h3>
                <#assign problems = review.getProblems()>
                <table class="table table-hover">
                    <tbody>
                    <#list problems as problem>
                        <tr class="${(problem.getType().name() == "ERROR")?then('table-danger',
                        (problem.getType().name() == "SUCCESS")?then('table-success', "table-warning"))}">
                            <th scope="row">${problem.getType().name()}</th>
                            <td>${problem.toString()}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </#if>
        </div>
    </form>

</@page.layout>