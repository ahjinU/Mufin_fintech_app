import { NavText } from '@/components';
import SignInForm from './SignInForm';

export default function SignIn() {
  return (
    <div className="">
      <SignInForm />
      <div className="flex flex-col gap-[0.4rem]">
        <div className="flex justify-between">
          <p className="custom-light-text">비밀번호를 잊으셨나요?</p>
          <NavText link="/" text="비밀번호 찾기" />
        </div>
        <div className="flex justify-between">
          <p className="custom-light-text">아직 회원이 아니신가요?</p>
          <NavText link="/" text="회원가입" />
        </div>
      </div>
    </div>
  );
}
