| 分支    | 命名规范    | 示例                  | 备注             |
| :------ | :---------- | :-------------------- | :--------------- |
| master  | master      | master                | 主分支           |
| develop | develop-*** | develop-20200220v1.3  | 以发布版本命名   |
| release | release-*** | release-20200223v2.1  | 以发布版本命名   |
| feature | feature-*** | feature-userinfov2.1  | 以主要功能点命名 |
| hotfix  | hotfix-***  | hotfix-userQueryError | 以修复功能命名   |

## master 分支

- master 为主分支，也是用于部署生产环境的分支，确保master分支稳定性
- master 分支一般由develop以及hotfix分支合并，任何时间都不能直接修改代码

## develop 分支

- develop 为开发分支，始终保持最新完成以及bug修复后的代码
- 一般开发的新功能时，feature分支都是基于develop分支下创建的

## feature 分支

- 开发新功能时，以develop为基础创建feature分支
- 分支命名: feature/ 开头的为特性分支， 命名规则: feature/user_module、 feature/cart_module

## release分支

- release 为预上线分支，发布提测阶段，会release分支代码为基准提测

> 当有一组feature开发完成，首先会合并到develop分支，进入提测时，会创建release分支。
> 如果测试过程中若存在bug需要修复，则直接由开发者在release分支修复并提交。
> 当测试完成之后，合并release分支到master和develop分支，此时master为最新代码，用作上线。

## hotfix 分支

- 分支命名: hotfix/ 开头的为修复分支，它的命名规则与 feature 分支类似
- 线上出现紧急问题时，需要及时修复，以master分支为基线，创建hotfix分支，修复完成后，需要合并到master分支和develop分支