import { useEffect, useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';
import { checkTelephoneParent, checkTelephoneChild } from '../../_apis/apis';
import { isValidPhoneNumber } from '../../_utils/validator';

export default function UserContact({
  onNext,
}: {
  onNext: (data: any) => void;
}) {
  const [contact, setContact] = useState({
    telephone: '',
    address: '',
    address2: '',
  });
  const [isValid, setIsValid] = useState(false);
  const [message, setMessage] = useState('');
  const [buttonMode, setButtonMode] = useState<'ACTIVE' | 'NON_ACTIVE'>(
    'NON_ACTIVE',
  );

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    const { name, value } = e.target;
    setContact({ ...contact, [name]: value });

  const checkTelephone = async () => {
    try {
      const fetchedData = await checkTelephoneParent(contact.telephone);
      if (fetchedData.ok) {
        setIsValid(true);
        setMessage('사용 가능한 번호입니다😀');
        console.log(fetchedData.headers.getSetCookie);
      } else {
        setIsValid(false);
        setMessage('중복된 번호입니다😢');
        console.log(fetchedData);
      }
    } catch (error) {
      console.error('전화번호 중복 검사 에러', error);
    }
  };

  const checkTelephone = async () => {
    if (isValidPhoneNumber(contact.telephone)) {
      try {
        const fetchedData = await checkTelephoneParent(contact.telephone);
        if (fetchedData.ok) {
          setIsValid(true);
          setMessage('사용 가능한 번호입니다😀');
          console.log(fetchedData.headers.getSetCookie);
        } else {
          setIsValid(false);
          setMessage('중복된 번호입니다😢');
          console.log(fetchedData);
        }
      } catch (error) {
        console.error('전화번호 중복 검사 에러', error);
      }
    }
  };

  useEffect(() => {
    if (
      isValidPhoneNumber(contact.telephone) &&
      isValid &&
      contact.address !== ''
    ) {
      setButtonMode('ACTIVE');
    } else {
      setButtonMode('NON_ACTIVE');
    }
  }, [contact, isValid]);

  const handleNext = () => {
    // if (buttonMode == 'ACTIVE') {
    //   onNext(contact);
    // }
    onNext(contact);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput
        label="전화번호"
        mode={isValid ? 'INFORM' : 'ERROR'}
        isMsg
        message={message}
      >
        <div className="flex items-center gap-[1rem]">
          <Input
            placeholder="'-'를 제외하고 입력해주세요"
            name="telephone"
            onChange={onChangeInput}
          />
          <TinyButton label="중복 확인" handleClick={checkTelephone} />
        </div>
      </ComplexInput>
      <ComplexInput label="주소" mode="NONE">
        <div className="flex flex-col gap-[0.6rem]">
          <div className="flex items-center gap-[1rem]">
            <Input
              placeholder="주소를 입력해주세요"
              name="address"
              onChange={onChangeInput}
            />
            <TinyButton label="주소 찾기" handleClick={() => {}} />
          </div>
          <Input
            placeholder="상세 주소를 입력해주세요"
            name="address2"
            onChange={onChangeInput}
          />
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Button label="다음" mode={buttonMode} onClick={handleNext} />
      </div>
    </div>
  );
}
