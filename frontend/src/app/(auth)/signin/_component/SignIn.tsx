import { ComplexInput, Input, NavText } from '@/components';
import SignInButton from './SignInButton';

export default function SignIn() {
  return (
    <div className="flex flex-col gap-[1rem]">
      <ComplexInput label="이메일" message="" mode="NONE">
        <Input placeholder="이메일을 입력해주세요" />
      </ComplexInput>
      <ComplexInput
        isMsg
        label="비밀번호"
        message="비밀번호를 다시 확인해 주세요!"
        mode="ERROR"
      >
        <Input placeholder="비밀번호를 입력해주세요" />
      </ComplexInput>
      <SignInButton />
      <div className='flex flex-col gap-[0.4rem]'>
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
