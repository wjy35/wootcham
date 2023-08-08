<template>
    <div>
        <div id="signupForm">
            <div class="emailInput">
                <input type="email" placeholder="이메일" v-model="emailInput" @keyup.enter="emailCheck">
                <SubmitButton v-if="!emailOk" class="innerButton" @click.prevent="emailCheck" value="중복 확인"></SubmitButton>
                <SubmitButton v-if="emailOk && !authOk" class="innerButton sendCode" @click.prevent="sendCode" value="코드 전송"></SubmitButton>
                <p class="emailOk" v-if="emailOk">사용 가능한 이메일입니다.</p>
            </div>
            <div class="authInput" v-if="!authOk">
                <input type="text" placeholder="인증 코드" v-model="emailAuth" @keyup.enter="authorize">
                <SubmitButton class="innerButton" @click.prevent="authorize" value="인증"></SubmitButton>
            </div>
            <p class="authOk" v-if="emailOk && authOk">{{ emailInput }} 인증 완료</p>
            <div class="pwInput">
                <input class="pw" type="password" placeholder="비밀번호" v-model="pwInput">
                <span @click.prevent="togglePwPattern" class="pwRule">{{ pwWarning }}</span>
                <p class="pwCondition" v-if="showPwPattern && pwWarning !== '사용 가능'">비밀번호는 8~16자의 영문 대소문자와 숫자, 특수문자를 사용하며,</p>
                <p class="pwCondition" v-if="showPwPattern && pwWarning !== '사용 가능'">특수문자는 한 개 이상 포함되어야 합니다.</p>
            </div>
            <div class="pwCheckInput">
                <input type="password" placeholder="비밀번호 확인" v-model="pwCheck">
                <span class="pwCheckWarning">{{ pwCheckWarning }}</span>
            </div>
            
            <div class="ruleInput">
                <label class="container">
                    <input id="ruleCheck" 
                    class="ruleCheck" 
                    checked="unchecked" 
                    type="checkbox"
                    v-model="agree"
                >
                    <div class="checkmark"></div>
                    <span class="agree">
                        <a class="toggleRules" @click.prevent="toggleRules">이용약관</a>에 동의합니다.
                    </span>
                </label>
            </div>
            
            <SubmitButton value="회원가입" @click="signup"></SubmitButton>
        </div>
        <CommonModal v-if="showRules">
            <h1>이 용 약 관</h1>
            <div class="ruleArea">‘웃참클럽’(이하 ‘회사’라 합니다)는 이용자의 개인정보를 중요시하며, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, 「개인정보 보호법」을 준수하기 위하여 노력하고 있습니다.<br>
            회사는 개인정보처리방침을 통하여 회사가 이용자로부터 제공받은 개인정보를 어떠한 용도와 방식으로 이용하고 있으며, 개인정보보호를 위해 어떠한 조치를 취하고 있는지 알려드립니다.<br>
            
            ## 0. 총칙<br>
            “개인정보”란 생존하는 개인에 관한 정보로서 당해 정보에 포함되어 있는 성명, 주민등록번호 등의 사항에 의하여 당해 개인을 식별할 수 있는 정보(당해 정보만으로는 특정 개인을 식별할 수 없더라도 다른 정보와 용이하게 결합하여 식별할 수 있는 것을 포함)를 말합니다.<br>
            회사는 개인정보처리방침을 지속적으로 개선하기 위하여 개정에 필요한 절차를 정하고 있습니다. 또한 개인정보처리방침을 개정하는 경우 이에 버전번호 등을 부여하여 이용자가 개정된 사항을 쉽게 알아볼 수 있게 합니다.<br>
            본 방침은 2023년 08월 01일부터 시행되며, 이를 개정하는 경우 웹사이트 공지사항(또는 이메일 등의 방법으로 개별공지)을 통하여 공지하겠습니다.<br>
            
            ## 1. 개인정보 자동수집 장치의 설치, 운영 및 그 거부에 관한 사항<br>
            회사는 이용자의 정보를 수시로 저장하고 찾아내는 ‘쿠키(cookie)’ 등을 운용합니다. 쿠키란 회사의 웹사이트를 운영하는데 이용되는 서버가 이용자의 브라우저에 보내는 아주 작은 텍스트 파일로, 이용자의 컴퓨터 하드디스크에 저장됩니다.<br>
            
            ### (1) 쿠키 등 사용 목적<br>
            회원의 접속 빈도나 방문 시간 등을 분석, 이용자의 취향과 관심분야를 파악 및 자취 추적, 각종 이벤트 참여 정도 및 방문 회수 파악 등을 통한 타겟 마케팅 및 개인 맞춤 서비스 제공<br>
            
            ### (2) 쿠키 설정 거부 방법<br>
            이용자는 쿠키 설치에 대한 선택권을 가지고 있습니다. 따라서 웹브라우저에서 옵션을 설정함으로써 모든 쿠키를 허용하거나, 쿠키가 저장될 때마다 확인을 거치거나, 아니면 모든 쿠키의 저장을 거부할 수도 있습니다. 단, 이용자께서 쿠키 설치를 거부하였을 경우 서비스 제공에 어려움이 있을 수 있습니다.<br>
            
            – 쿠키설정 방법 (인터넷 익스플로러 11.0을 사용하고 있는 경우)<br>
            : 「도구」 메뉴에서「인터넷옵션」을 선택합니다. 「개인정보 탭」을 클릭합니다.<br>
            「고급」을 선택하여 본인에게 맞는 쿠키허용 수준을 설정하시면 됩니다.<br>
            – 받은 쿠키를 보는 방법 (인터넷 익스플로러 11.0을 사용하고 있는 경우)<br>
            : 「도구」 메뉴에서 「인터넷옵션」을 선택합니다.<br>
            「일반」 탭을 클릭하여 ‘검색기록’의 「설정」으로 들어가서, 「파일보기」를 통해 확인합니다.<br>
            – 쿠키 설정 거부 방법 (인터넷 익스플로러 11.0을 사용하고 있는 경우)<br>
            : 「도구」 메뉴에서 「인터넷옵션」을 선택합니다. 「개인정보 탭」을 클릭합니다.<br>
            「기본값」을 선택하여 상위레벨로 하여 “모든 쿠키차단”으로 설정하시면 됩니다.<br>
            
            ## 2. 수집하는 개인정보의 항목 및 수집방법<br>
            ### (1) 수집하는 개인정보의 항목<br>
            회사는 최초 회원가입 또는 서비스 이용 시 이용자로부터 아래와 같은 개인정보를 수집하고 있습니다.<br>
            
            - E-mail(ID)<br>
            - 비밀번호<br>
            - 닉네임<br>
            
            ## 3. 개인정보의 보유 및 이용기간<br>
            원칙적으로, 회사는 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다.<br>
            단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우, 회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 이 경우 회사는 해당 개인정보를 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.</div>
        
            <SubmitButton class='close' @click.prevent="toggleRules" value="닫기"></SubmitButton>
        </CommonModal>
        <CommonModal v-if="checkSend">
            <p class="sendConfirm">{{ emailInput }}으로 이메일 인증 코드를 전송하시겠습니까?</p>
            <SubmitButton class='close' @click.prevent="sendYes" value="보내기"></SubmitButton>
            <SubmitButton class='close' @click.prevent="sendNo" value="닫기"></SubmitButton>
        </CommonModal>
    </div>
</template>
<script>
import SubmitButton from './UI/SubmitButton.vue';
import CommonModal from './UI/CommonModal.vue';

const regPass = /^(?=.*[a-zA-Z])(?=.*[\W_]).{8,16}$/;

export default {
    name: 'SignupForm',
    data() {
        return {
            emailInput: "",
            emailOk: undefined,
            emailAuth: "",
            authOk: false,
            pwInput: "",
            pwCheck: "",
            pwWarning: "생성 규칙",
            pwCheckWarning: "",
            agree: false,
            showRules: false,
            showPwPattern: false,
            checkSend: false,
        }
    },
    components: {
        SubmitButton, CommonModal
    },  

    watch: {
        emailInput() {
            this.emailOk = undefined;
        },

        pwInput() {
            if (this.pwInput.length > 0 && !regPass.test(this.pwInput)) {
                document.querySelector(".pwRule").classList.remove("pwOk");
                document.querySelector(".pwRule").classList.add("pwNotok");
                this.pwWarning = "사용 불가";
                this.pwCheckWarning = "";
            } else if (this.pwInput.length === 0) {
                document.querySelector(".pwRule").classList.remove("pwOk");
                document.querySelector(".pwRule").classList.remove("pwNotok");
                this.pwWarning = "생성 규칙"
                this.pwCheckWarning = "";
            } else {
                document.querySelector(".pwRule").classList.add("pwOk");
                document.querySelector(".pwRule").classList.remove("pwNotok");
                this.pwWarning = "사용 가능";
                this.showPwPattern = false;
                if (this.pwCheck.length > 0 && this.pwInput !== this.pwCheck) {
                    document.querySelector(".pwCheckWarning").classList.remove("ok");
                    this.pwCheckWarning = "불일치"
                } else if (this.pwCheck.length === 0) {
                    this.pwCheckWarning = "";
                } else {
                    document.querySelector(".pwCheckWarning").classList.add("ok");
                    this.pwCheckWarning = "일치";
                }
            }
        },
        pwCheck() {
            if (this.pwWarning !== "사용 불가") {
                if (this.pwCheck.length > 0 && this.pwInput !== this.pwCheck) {
                    document.querySelector(".pwCheckWarning").classList.remove("ok");
                    this.pwCheckWarning = "불일치"
                } else if (this.pwCheck.length === 0) {
                    this.pwCheckWarning = "";
                } else {
                    document.querySelector(".pwCheckWarning").classList.add("ok");
                    this.pwCheckWarning = "일치";
                }
            } else {
                this.pwCheckWarning = "";
            }
        }
    },

    methods: {
        signup() {
            if (this.emailOk && this.authOk && this.pwCheckWarning === "일치" && this.agree) {
                alert('회원가입 성공');
            } else {
                alert('회원가입 실패');
            }
        },

        sendCode() {
            this.checkSend = true;
        },

        sendYes() {
            // 인증 코드 보내기
            this.checkSend = false;
        },

        sendNo() {
            this.checkSend = false;
        },

        authorize() {
            if (this.emailAuth.length === 0) {
                alert('인증 코드를 입력해주세요')
                this.authOk = false;
            } else if (this.emailAuth === "ssafy") {
                alert('인증 완료')
                this.authOk = true;
                this.emailFinal = this.emailInput;
                this.emailAuth = "";
            } else {
                alert('틀린 코드입니다.')
                this.authOk = false;
            }
        },

        toggleRules() {
            this.showRules = !this.showRules;
        },
        
        togglePwPattern() {
            if (this.pwWarning !== "사용 가능") {
                this.showPwPattern = !this.showPwPattern;
            }
        },

        emailCheck() {
            if (this.emailInput.length === 0) {
                alert('이메일을 입력해주세요.')
            } else if (!this.emailInput.includes('@')) {
                alert('이메일 형식을 지켜주세요')
                this.emailOk = false;
            } else if (this.emailInput === 'ssafy@ssafy.com') {
                alert('중복')
                this.emailOk = false;
            } else {
                alert('사용 가능한 이메일입니다.')
                this.emailOk = true;
            }
            this.authOk = false;
        }
    }
}
</script>

<style scoped>
    #signupForm {
        width: 70%;
        margin: auto;
    }

    #signupForm input {
        background-color: #FFF2EA;
    }

    #signupButton {
        width: 100%;
    }

    .innerButton{
        font-size: 1.2rem;
        width: auto;
        position: absolute;
        right: 0.5rem;
    }

    .sendCode {
        background-color: #F27059;
    }

    .emailInput{
        position: relative;
    }

    .authInput {
        position: relative;
    }

    .pwInput {
        position: relative;
    }

    .pwCheckInput {
        position: relative;
    }

    .pwRule {
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: black;
        text-decoration: underline;
    }

    .pwRule:hover {
        cursor: pointer;
    }
    .pwNotok {
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: red;
        text-decoration: none;
    }

    .pwOk {
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: black;
        text-decoration: none;
    }

    .pwOk:hover {
        cursor: auto;
    }

    .pwCheckWarning{
        position: absolute;
        right: 0.5rem;
        top: 1.5rem;
        color: red;
    }

    .ok {
        color: black;
        text-decoration: none;
    }
    .pwCondition {
        margin: 0;
        font-size: 0.8rem;
        text-align: left;
    }

    /* CHECKBOX */
    /* Hide the default checkbox */
    .container input {
    /* position: absolute; */
    opacity: 0;
    cursor: pointer;
    height: 0;
    width: 0;
    }

    .container {
    display: flex;
    gap: 10px;
    justify-content: center;
    align-items: center;
    }


    /* Create a custom checkbox */
    .checkmark {
    position: relative;
    box-shadow: rgb(255, 84, 0) 0px 0px 0px 2px;
    background-color: rgba(16, 16, 16, 0.5);
    height: 20px;
    width: 20px;
    margin-right: 10px;
    flex-shrink: 0;
    margin-top: -1px;
    transition: all 0.2s ease 0s;
    cursor: pointer;
    transform-origin: 0px 10px;
    border-radius: 4px;
    margin: -1px 10px 0px 0px;
    padding: 0px;
    box-sizing: border-box;
    }

    .container input:checked ~ .checkmark {
    box-shadow: rgb(255, 84, 0) 0px 0px 0px 2px;
    background-color: rgba(245, 24, 24, 0.5);
    height: 20px;
    width: 20px;
    margin-right: 10px;
    flex-shrink: 0;
    margin-top: -1px;
    transition: all 0.2s ease 0s;
    cursor: pointer;
    transform-origin: 0px 10px;
    border-radius: 4px;
    margin: -1px 10px 0px 0px;
    padding: 0px;
    box-sizing: border-box;
    }

    .checkmark:after {
    content: "";
    position: absolute;
    display: none;
    }

    .container input:checked ~ .checkmark:after {
    display: block;
    }

    /* Style the checkmark/indicator */
    .container .checkmark:after {
    left: 0.45em;
    top: 0.25em;
    width: 0.25em;
    height: 0.5em;
    border: solid white;
    border-width: 0 0.15em 0.15em 0;
    transform: rotate(45deg);
    transition: all 500ms ease-in-out;
    }

    .agree {
        margin: 0.5rem;
    }


    .toggleRules {
        text-decoration: underline;
        color: #F27059;
    }

    .toggleRules:hover {
        cursor: pointer;
    }

    .emailOk, .authOk {
        font-size: 0.8rem;
        margin: 0;
        text-align: left;
    }

    .close {
        background-color: #FFF2EA;
        width: auto;
        color: #F27059;
        font-size: 1.5rem;
        margin: 1rem;
    }

    h1 {
        font-size: 2rem;
        color: #FFF2EA;
        margin: 1rem;
    }

    .ruleArea {
        background-color: #FFF2EA;
        color: black;
        max-height: 29vh; 
        overflow: auto;
        padding: 1rem;
        margin-left: 1rem;
        margin-right: 1rem;
        text-align: left;
    }

    .sendConfirm {
        color: #FFF2EA;
    }
</style>