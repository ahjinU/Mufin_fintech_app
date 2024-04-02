import { useEffect, useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';
import { checkTelephoneParent, checkTelephoneChild } from '../../_apis/apis';
import { isValidPhoneNumber } from '../../_utils/validator';
import { useSession } from 'next-auth/react';
import { useDaumPostcodePopup } from 'react-daum-postcode';

export default function UserContact({
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

    if (name === 'telephone') {
      setMessage(
        isValidPhoneNumber(value)
          ? ''
          : '전화번호는 11자리의 숫자로 입력해주세요!',
      );
    }
  };

  const checkTelephone = async () => {
    if (isValidPhoneNumber(contact.telephone)) {
      try {
        let fetchedData;
        if (forParent) {
          fetchedData = await checkTelephoneParent(contact.telephone);
        } else {
          fetchedData = await checkTelephoneChild(
            Authorization,
            contact.telephone,
          );
        }
        if (fetchedData.ok) {
          setIsValid(true);
          setMessage('사용 가능한 번호입니다😀');
          // console.log(fetchedData.headers.getSetCookie);
        } else {
          setIsValid(false);
          setMessage('중복된 번호입니다😢');
          // console.log(fetchedData);
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
    if (buttonMode == 'ACTIVE') {
      onNext(contact);
    }
  };

  const open = useDaumPostcodePopup();

  const handleComplete = (data: any) => {
    let fullAddress = data.address;
    let extraAddress = '';

    if (data.addressType === 'R') {
      if (data.bname !== '') {
        extraAddress += data.bname;
      }
      if (data.buildingName !== '') {
        extraAddress +=
          extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== '' ? ` (${extraAddress})` : '';
    }
    setContact({ ...contact, address: fullAddress });
  };

  const handleClick = () => {
    open({ onComplete: handleComplete });
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
            type="tel"
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
              type="text"
              placeholder="주소를 입력해주세요"
              name="address"
              value={contact.address}
              reset={false}
            />
            <TinyButton label="주소 찾기" handleClick={handleClick} />
          </div>
          <Input
            type="text"
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
