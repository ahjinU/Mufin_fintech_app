import Link from 'next/link';
import { useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';
import { checkEmailParent, checkEmailChild } from '../../_apis/apis';

export default function UserAccount({
  onNext,
}: {
  onNext: (data: any) => void;
}) {
  const [account, setAccount] = useState({ email: '', password: '' });
  const [isValid, setIsValid] = useState(false);
  const [message, setMessage] = useState('');

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    setAccount({ ...account, [e.target.name]: e.target.value });
  };

  const checkEmail = async () => {
    try {
      const fetchedData = await checkEmailParent(account.email);
      if (fetchedData.ok) {
        setIsValid(true);
        setMessage('사용 가능한 이메일입니다😀');
      } else {
        setIsValid(false);
        setMessage('중복된 이메일입니다😢');
      }
    } catch (error) {
      console.error('이메일 중복 검사 에러', error);
    }
  };

  const checkPassword = () => {};

  const handleNext = () => {
    onNext(account);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput
        label="이메일"
        mode={isValid ? 'INFORM' : 'ERROR'}
        isMsg
        message={message}
      >
        <div className="flex items-center gap-[1rem]">
          <Input
            placeholder="이메일을 입력해주세요"
            name="email"
            onChange={onChangeInput}
          />
          <TinyButton label="중복 확인" onClick={checkEmail} />
        </div>
      </ComplexInput>
      <ComplexInput label="비밀번호" mode="NONE">
        <Input
          placeholder="비밀번호를 입력해주세요"
          name="password"
          onChange={onChangeInput}
        />
      </ComplexInput>
      <ComplexInput label="비밀번호 확인" mode="NONE">
        <Input
          placeholder="비밀번호를 한번 더 입력해주세요"
          name="password"
          onChange={onChangeInput}
        />
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Link href="/signup/check">
          <Button label="다음" mode="ACTIVE" onClick={handleNext} />
        </Link>
      </div>
    </div>
  );
}
