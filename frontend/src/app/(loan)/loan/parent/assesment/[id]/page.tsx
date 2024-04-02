'use client';

import useFetch from '@/hooks/useFetch';
import { useEffect, useState } from 'react';
import { RequestListType } from '../../_types/types';

import AssesmentInfo from '../_component/AssesmentInfo';
import AssesmentChat from '../_component/AssesmentChat';
import AssesmentCheck from '../_component/AssesmentCheck';
import AssesmentRefusal from '../_component/AssesmentRefusal';
import AssesmentComplete from '../_component/AssesmentComplete';
import { NavBar } from '@/components';
import useUserStore from '@/app/_store/store';

export default function Page({
  params: { id },
}: {
  params: {
    id: number;
  };
}) {
  const [state, setState] = useState<
    'INFO' | 'CHATBOT' | 'CHECK' | 'REFUSAL' | 'COMPLETE'
  >('INFO');
  const [requestData, setRequestData] = useState<RequestListType>();
  const [isApproved, setIsApproved] = useState(false);
  const { postFetch } = useFetch();
  const { userData } = useUserStore();

  useEffect(() => {
    const fetchLoanData = async () => {
      try {
        const res = await postFetch({ api: '/loan/requests/parents' });
        if (res?.data?.loansList) {
          setRequestData(res.data.loansList[id]);
        }
      } catch (error) {
        console.error('아이 대출 요청 가져오기 에러', error);
      }
    };
    fetchLoanData();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="relative">
      {state == 'INFO' && requestData ? (
        <AssesmentInfo
          childInfo={requestData}
          buttonClick={() => setState('CHATBOT')}
        />
      ) : null}
      {(state == 'CHATBOT' || state == 'CHECK' || state == 'REFUSAL') &&
      requestData ? (
        <AssesmentChat
          childInfo={requestData}
          buttonClick={() => setState('CHECK')}
        />
      ) : null}
      {state == 'CHECK' && requestData ? (
        <AssesmentCheck
          uuid={requestData?.loanUuid}
          goToComplete={() => {
            setIsApproved(true);
            setState('COMPLETE');
          }}
          buttonClickNo={() => setState('REFUSAL')}
          goBack={() => {
            setState('CHATBOT');
          }}
        />
      ) : null}
      {state == 'REFUSAL' && requestData ? (
        <AssesmentRefusal
          uuid={requestData?.loanUuid}
          goToComplete={() => {
            setIsApproved(false);
            setState('COMPLETE');
          }}
          goBack={() => {
            setState('CHATBOT');
          }}
        />
      ) : null}
      {state == 'COMPLETE' && requestData ? (
        <div className="relative">
          <div className="min-h-[calc(100vh-11.6rem)]">
            <AssesmentComplete isApproved={isApproved} />
          </div>
          <div className="mx-[-1.2rem]">
            <NavBar mode={userData.isParent ? 'PARENT' : 'CHILD'} />
          </div>
        </div>
      ) : null}
    </div>
  );
}
