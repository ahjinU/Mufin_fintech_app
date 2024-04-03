import Link from 'next/link';
import { useEffect, useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';
import { checkEmailParent, checkEmailChild } from '../../_apis/apis';
import { isValidEmail, isValidPassword } from '../../_utils/validator';
import { useSession } from 'next-auth/react';

export default function UserAccount({
  onNext,
  forParent,
}: {
  onNext: (data: any) => void;
  forParent: boolean;
}) {
  const { data: session } = useSession();
  let Authorization: string;
  if (session?.Authorization) {
    Authorization = session.Authorization;
  }

  const [account, setAccount] = useState({
    email: '',
    password: '',
    password2: '',
  });
  const [isValid, setIsValid] = useState(false);
  const [message, setMessage] = useState({
    emailMessage: '',
    passwordMessage: '',
    checkMessage: '',
  });
  const [buttonMode, setButtonMode] = useState<'ACTIVE' | 'NON_ACTIVE'>(
    'NON_ACTIVE',
  );
  const [isCheckValid, setIsCheckValid] = useState(false);

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    const { name, value } = e.target;
    setAccount({ ...account, [name]: value });

    if (name === 'email') {
      setMessage({
        ...message,
        emailMessage: isValidEmail(value)
          ? ''
          : '올바른 이메일 형식이 아니에요!',
      });
    } else if (name === 'password') {
      setMessage({
        ...message,
        passwordMessage: isValidPassword(value)
          ? ''
          : '영문, 숫자, 특수문자를 포함해서 6~12자로 입력해주세요!',
      });
    } else if (name === 'password2') {
      setMessage({
        ...message,
        checkMessage:
          account.password == value ? '' : '비밀번호가 일치하지 않아요!',
      });
    }
  };

  const checkEmail = async () => {
    if (isValidEmail(account.email)) {
      try {
        let fetchedData;
        if (forParent) {
          fetchedData = await checkEmailParent(account.email);
        } else {
          fetchedData = await checkEmailChild(Authorization, account.email);
        }
        if (fetchedData.ok) {
          setIsValid(true);
          setIsCheckValid(true);
          setMessage({
            ...message,
            emailMessage: '사용 가능한 이메일입니다😀',
          });
        } else {
          setIsValid(false);
          setMessage({
            ...message,
            emailMessage: '중복된 이메일입니다😢',
          });
        }
      } catch (error) {
        console.error('이메일 중복 검사 에러', error);
      }
    }
  };

  useEffect(() => {
    if (
      isValidEmail(account.email) &&
      isValidPassword(account.password) &&
      isValid
    ) {
      setButtonMode('ACTIVE');
    } else {
      setButtonMode('NON_ACTIVE');
    }
  }, [account.email, account.password, isValid]);

  const handleNext = () => {
    onNext(account);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput
        label="이메일"
        mode={isValid ? 'INFORM' : 'ERROR'}
        isMsg
        message={message.emailMessage}
      >
        <div className="flex items-center gap-[1rem]">
          <Input
            placeholder="이메일을 입력해주세요"
            name="email"
            onChange={onChangeInput}
          />
          {!setIsCheckValid && (
            <TinyButton label="중복 확인" handleClick={checkEmail} />
          )}
        </div>
      </ComplexInput>
      <ComplexInput
        label="비밀번호"
        mode="ERROR"
        isMsg
        message={message.passwordMessage}
      >
        <Input
          type="password"
          placeholder="비밀번호를 입력해주세요"
          name="password"
          onChange={onChangeInput}
          reset={false}
        />
      </ComplexInput>
      <ComplexInput
        label="비밀번호 확인"
        mode="ERROR"
        isMsg
        message={message.checkMessage}
      >
        <Input
          type="password"
          placeholder="비밀번호를 한번 더 입력해주세요"
          name="password2"
          onChange={onChangeInput}
          reset={false}
        />
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        {buttonMode == 'ACTIVE' ? (
          <Link href="/signup/check">
            <Button label="다음" mode={buttonMode} onClick={handleNext} />
          </Link>
        ) : (
          <Button label="다음" mode={buttonMode} onClick={handleNext} />
        )}
      </div>
    </div>
  );
}
